package com.ytrue.yadmin.modules.generator.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.core.enums.DatabaseType;
import com.ytrue.yadmin.core.enums.ResponseCode;
import com.ytrue.yadmin.core.utils.AssertUtils;
import com.ytrue.yadmin.core.utils.db.DbUtils;
import com.ytrue.yadmin.core.utils.query.QueryEntity;
import com.ytrue.yadmin.modules.generator.dao.GenDataSourceDao;
import com.ytrue.yadmin.modules.generator.model.GenBaseClass;
import com.ytrue.yadmin.modules.generator.model.GenDataSource;
import com.ytrue.yadmin.modules.generator.service.GenDataSourceService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author ytrue
 * @date 2022/5/19 16:41
 * @description GenDataSourceServiceImpl
 */
@Service
public class GenDataSourceServiceImpl extends ServiceImpl<GenDataSourceDao, GenDataSource> implements GenDataSourceService {


    @Override
    public IPage<GenDataSource> paginate(QueryEntity<GenDataSource> queryEntity) {
        queryEntity = Objects.isNull(queryEntity) ? new QueryEntity<>() : queryEntity;
        return page(queryEntity.getPage(), queryEntity.getQueryModel());
    }

    @SneakyThrows
    @Override
    public void testDatabaseConnect(Long id) {
        GenDataSource dataSource = getById(id);
        //获取连接,这里只测试mysql的连接，这里的异常要处理的，暂时不处理
        AssertUtils.strEq(dataSource.getDbType(), DatabaseType.MySQL.toString(), ResponseCode.ONLY_SUPPORT_MYSQL);
        DbUtils.getConnection(
                dataSource.getConnUrl(),
                dataSource.getUsername(),
                dataSource.getPassword(), DatabaseType.MySQL.getDriverClass()
        );

    }
}
