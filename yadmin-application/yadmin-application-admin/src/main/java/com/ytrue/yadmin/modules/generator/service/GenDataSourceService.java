package com.ytrue.yadmin.modules.generator.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ytrue.yadmin.tools.query.entity.QueryEntity;
import com.ytrue.yadmin.modules.generator.model.GenDataSource;

/**
 * @author ytrue
 * @date 2022/5/19 16:39
 * @description GenDataSourceService
 */
public interface GenDataSourceService extends IService<GenDataSource> {

    /**
     * 分页查询
     *
     * @param queryEntity
     * @return
     */
    IPage<GenDataSource> paginate(QueryEntity<GenDataSource> queryEntity);

    /**
     * 测试数据库连接
     *
     * @param id
     */
    void testDatabaseConnect(Long id);
}
