package com.ytrue.yadmin.modules.generator.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ytrue.yadmin.tools.query.entity.QueryEntity;
import com.ytrue.yadmin.modules.generator.model.GenTableInfo;
import com.ytrue.yadmin.modules.generator.model.dto.request.ImportTableRequest;

import java.util.List;

/**
 * @author ytrue
 * @date 2022/5/27 11:36
 * @description TableInfoService
 */
public interface GenTableInfoService extends IService<GenTableInfo> {


    /**
     * 分页查询
     *
     * @param queryEntity
     * @return
     */
    IPage<GenTableInfo> paginate(QueryEntity<GenTableInfo> queryEntity);

    /**
     * 获取数据源所有的表信息
     *
     * @param id
     * @return
     */
    List<GenTableInfo> getDataSourceTables(Long id);


    /**
     * 导入表
     *
     * @param importTableRequest
     */
    void importTable(ImportTableRequest importTableRequest);

    /**
     * 批量删除表和对应的字段
     *
     * @param ids
     */
    void removeBatchTableAndFieldByIds(List<Long> ids);
}
