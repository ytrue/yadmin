package com.ytrue.yadmin.modules.generator.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.core.enums.ResponseCode;
import com.ytrue.yadmin.core.utils.AssertUtils;
import com.ytrue.yadmin.modules.generator.dao.GenFieldTypeDao;
import com.ytrue.yadmin.modules.generator.dao.GenTableFieldDao;
import com.ytrue.yadmin.modules.generator.dao.GenTableInfoDao;
import com.ytrue.yadmin.modules.generator.model.GenFieldType;
import com.ytrue.yadmin.modules.generator.model.GenTableField;
import com.ytrue.yadmin.modules.generator.model.GenTableInfo;
import com.ytrue.yadmin.modules.generator.model.dto.ImportTableRequest;
import com.ytrue.yadmin.modules.generator.model.dto.config.GeneratorConfigDTO;
import com.ytrue.yadmin.modules.generator.service.GenTableInfoService;
import com.ytrue.yadmin.modules.generator.service.manager.DbManager;
import com.ytrue.yadmin.modules.generator.service.manager.GeneratorConfigManger;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ytrue
 * @date 2022/5/19 16:41
 * @description GenTableInfoServiceImpl
 */
@Service
@AllArgsConstructor
public class GenTableInfoServiceImpl extends ServiceImpl<GenTableInfoDao, GenTableInfo> implements GenTableInfoService {

    private final DbManager dbManager;
    private final GeneratorConfigManger generatorConfigManger;
    private final GenFieldTypeDao genFieldTypeDao;
    private final GenTableFieldDao genTableFieldDao;

    @Override
    public List<GenTableInfo> getDataSourceTables(Long id) {
        return dbManager.getDataSourceTables(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void importTable(ImportTableRequest importTableRequest) {

        GenTableInfo table = getOne(
                new LambdaQueryWrapper<GenTableInfo>()
                        .eq(GenTableInfo::getDatasourceId, importTableRequest.getDatasourceId())
                        .eq(GenTableInfo::getTableName, importTableRequest.getTableName()));
        //验证表是否在数据库存在
        AssertUtils.isNull(table, ResponseCode.TABLE_EXISTS);

        GenTableInfo tableInfo = dbManager.getDataSourceTable(importTableRequest.getDatasourceId(), importTableRequest.getTableName());
        //获取原生列数据
        List<GenTableField> tableFieldList = dbManager.getTableColumns(importTableRequest.getDatasourceId(), tableInfo.getId(), tableInfo.getTableName());
        //代码生成器信息
        GeneratorConfigDTO generatorConfig = generatorConfigManger.getGeneratorConfig();

        //保存表信息
        tableInfo.setPackageName(generatorConfig.getProject().getPackageName());
        tableInfo.setVersion(generatorConfig.getProject().getVersion());
        tableInfo.setBackendPath(generatorConfig.getProject().getBackendPath());
        tableInfo.setFrontendPath(generatorConfig.getProject().getFrontendPath());
        tableInfo.setAuthor(generatorConfig.getDeveloper().getAuthor());
        tableInfo.setEmail(generatorConfig.getDeveloper().getEmail());
        //保存到数据库
        save(tableInfo);
        //初始化列数据
        initFieldList(tableFieldList);
        // 批量插入
        tableFieldList.forEach(genTableFieldDao::insert);
    }

    /**
     * 初始化列数据
     *
     * @param tableFieldList
     */
    private void initFieldList(List<GenTableField> tableFieldList) {
        //字段类型、属性类型映射
        List<GenFieldType> fieldTypeList = genFieldTypeDao.selectList(null);
        Map<String, GenFieldType> fieldTypeMap = new LinkedHashMap<>(fieldTypeList.size());
        fieldTypeList.forEach(fieldType -> fieldTypeMap.put(fieldType.getColumnType().toLowerCase(), fieldType));

        AtomicInteger index = new AtomicInteger();
        tableFieldList.forEach(tableField -> {
            //下划线转小驼峰
            tableField.setAttrName(StrUtil.toCamelCase(tableField.getColumnName()));
            //获取字段对应的类型
            GenFieldType fieldTypeMapping = fieldTypeMap.get(tableField.getColumnType().toLowerCase());


            if (fieldTypeMapping == null) {
                //没找到对应的类型，则为Object类型
                tableField.setAttrType("Object");
                //tableField.setPackageName("java.lang.Object");
            } else {
                tableField.setAttrType(fieldTypeMapping.getAttrType());
                tableField.setPackageName(fieldTypeMapping.getPackageName());
            }

            tableField.setList(true);
            tableField.setForm(true);

            tableField.setQueryType("=");
            tableField.setFormType("text");

            tableField.setSort(index.getAndIncrement());
        });
    }

}
