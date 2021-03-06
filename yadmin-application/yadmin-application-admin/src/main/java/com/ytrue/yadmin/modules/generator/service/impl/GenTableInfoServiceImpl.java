package com.ytrue.yadmin.modules.generator.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.core.enums.ResponseCode;
import com.ytrue.yadmin.core.utils.AssertUtils;
import com.ytrue.yadmin.tools.query.entity.QueryEntity;
import com.ytrue.yadmin.modules.generator.dao.GenFieldTypeDao;
import com.ytrue.yadmin.modules.generator.dao.GenTableFieldDao;
import com.ytrue.yadmin.modules.generator.dao.GenTableInfoDao;
import com.ytrue.yadmin.modules.generator.model.GenFieldType;
import com.ytrue.yadmin.modules.generator.model.GenTableField;
import com.ytrue.yadmin.modules.generator.model.GenTableInfo;
import com.ytrue.yadmin.modules.generator.model.dto.request.ImportTableRequest;
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
import java.util.Objects;
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
    public IPage<GenTableInfo> paginate(QueryEntity<GenTableInfo> queryEntity) {
        queryEntity = Objects.isNull(queryEntity) ? new QueryEntity<>() : queryEntity;
        return page(queryEntity.getPage(), queryEntity.getQueryModel());
    }

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
        //?????????????????????????????????
        AssertUtils.isNull(table, ResponseCode.TABLE_EXISTS);

        GenTableInfo tableInfo = dbManager.getDataSourceTable(importTableRequest.getDatasourceId(), importTableRequest.getTableName());
        //?????????????????????
        GeneratorConfigDTO generatorConfig = generatorConfigManger.getGeneratorConfig();

        //???????????????
        tableInfo.setPackageName(generatorConfig.getProject().getPackageName());
        tableInfo.setVersion(generatorConfig.getProject().getVersion());
        tableInfo.setBackendPath(generatorConfig.getProject().getBackendPath());
        tableInfo.setFrontendPath(generatorConfig.getProject().getFrontendPath());
        tableInfo.setAuthor(generatorConfig.getDeveloper().getAuthor());
        tableInfo.setEmail(generatorConfig.getDeveloper().getEmail());
        //??????????????????
        save(tableInfo);
        //?????????????????????
        List<GenTableField> tableFieldList = dbManager.getTableColumns(importTableRequest.getDatasourceId(), tableInfo.getId(), tableInfo.getTableName());
        //??????????????????
        initFieldList(tableFieldList);
        // ????????????
        tableFieldList.forEach(genTableFieldDao::insert);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeBatchTableAndFieldByIds(List<Long> ids) {
        removeBatchByIds(ids);
        genTableFieldDao.delete(new LambdaQueryWrapper<GenTableField>().in(GenTableField::getTableId, ids));
    }

    /**
     * ??????????????????
     *
     * @param tableFieldList
     */
    private void initFieldList(List<GenTableField> tableFieldList) {
        //?????????????????????????????????
        List<GenFieldType> fieldTypeList = genFieldTypeDao.selectList(null);
        Map<String, GenFieldType> fieldTypeMap = new LinkedHashMap<>(fieldTypeList.size());
        fieldTypeList.forEach(fieldType -> fieldTypeMap.put(fieldType.getColumnType().toLowerCase(), fieldType));

        AtomicInteger index = new AtomicInteger();
        tableFieldList.forEach(tableField -> {
            //?????????????????????
            tableField.setAttrName(StrUtil.toCamelCase(tableField.getColumnName()));
            //???????????????????????????
            GenFieldType fieldTypeMapping = fieldTypeMap.get(tableField.getColumnType().toLowerCase());


            if (fieldTypeMapping == null) {
                //?????????????????????????????????Object??????
                tableField.setAttrType("Object");
                //tableField.setPackageName("java.lang.Object");
            } else {
                tableField.setAttrType(fieldTypeMapping.getAttrType());
                tableField.setPackageName(fieldTypeMapping.getPackageName());
            }

            tableField.setIsList(true);
            tableField.setIsForm(true);

            tableField.setQueryType("=");
            tableField.setFormType("text");

            tableField.setSort(index.getAndIncrement());
        });
    }

}
