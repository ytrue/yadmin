package com.ytrue.yadmin.modules.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ytrue.yadmin.modules.generator.model.GenDataSource;

import java.sql.SQLException;

/**
 * @author ytrue
 * @date 2022/5/19 16:39
 * @description GenDataSourceService
 */
public interface GenDataSourceService extends IService<GenDataSource> {
    /**
     * 测试数据库连接
     *
     * @param id
     */
    void testDatabaseConnect(Long id);
}
