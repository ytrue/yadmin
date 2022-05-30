package com.ytrue.yadmin.modules.generator.service.manager;

import cn.hutool.core.util.StrUtil;
import com.ytrue.yadmin.core.enums.ResponseCode;
import com.ytrue.yadmin.core.utils.AssertUtils;
import com.ytrue.yadmin.core.utils.db.query.Query;
import com.ytrue.yadmin.modules.generator.dao.GenDataSourceDao;
import com.ytrue.yadmin.modules.generator.model.GenDataSource;
import com.ytrue.yadmin.modules.generator.model.dto.DataSourceInfoDTO;
import com.ytrue.yadmin.modules.generator.model.dto.TableInfoDTO;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ytrue
 * @date 2022/5/27 17:03
 * @description DbManager
 */
@Component
@AllArgsConstructor
public class DbManager {

    private final DataSource dataSource;
    private final GenDataSourceDao genDataSourceDao;



    /**
     * 获取数据库表信息
     *
     * @param id
     * @param tableName
     * @return
     */
    @SneakyThrows
    public TableInfoDTO getDataSourceTable(Long id, String tableName) {
        DataSourceInfoDTO dataSourceInfo = getDataSourceInfo(id);

        Query dbQuery = dataSourceInfo.getDbQuery();
        //查询数据
        PreparedStatement preparedStatement = dataSourceInfo.getConnection().prepareStatement(dbQuery.tablesSql(tableName));
        ResultSet rs = preparedStatement.executeQuery();
        //填充
        if (rs.next()) {
            return createTableInfo(rs, dbQuery, id);
        }

        return null;
    }


    /**
     * 获取数据源所有的表信息
     *
     * @param id
     * @return
     */
    @SneakyThrows
    public List<TableInfoDTO> getDataSourceTables(Long id) {
        DataSourceInfoDTO dataSourceInfo = getDataSourceInfo(id);

        List<TableInfoDTO> tableInfoList = new ArrayList<>();

        Query dbQuery = dataSourceInfo.getDbQuery();
        //查询数据
        PreparedStatement preparedStatement = dataSourceInfo.getConnection().prepareStatement(dbQuery.tablesSql(null));
        ResultSet rs = preparedStatement.executeQuery();
        //填充
        while (rs.next()) {
            TableInfoDTO tableInfo = createTableInfo(rs, dbQuery, id);
            tableInfoList.add(tableInfo);
        }
        //关闭
        dataSourceInfo.getConnection().close();
        return tableInfoList;
    }


    /**
     * 获取数据源
     *
     * @param id
     * @return
     * @throws Exception
     */
    public DataSourceInfoDTO getDataSourceInfo(Long id) throws Exception {
        DataSourceInfoDTO dataSourceInfo;
        if (id.intValue() == 0) {
            dataSourceInfo = new DataSourceInfoDTO(dataSource.getConnection());
        } else {
            GenDataSource genDataSource = genDataSourceDao.selectById(id);
            AssertUtils.notNull(genDataSource, ResponseCode.DATA_NOT_FOUND);
            dataSourceInfo = new DataSourceInfoDTO(genDataSource);
        }
        return dataSourceInfo;
    }


    /**
     * 创建TableInfoDTO
     *
     * @param rs
     * @param dbQuery
     * @param id
     * @return
     * @throws Exception
     */
    private TableInfoDTO createTableInfo(ResultSet rs, Query dbQuery, Long id) throws Exception {
        TableInfoDTO tableInfo = new TableInfoDTO();
        tableInfo.setTableName(rs.getString(dbQuery.tableName()));
        //下划线转小驼峰，首字符大写
        tableInfo.setClassName(StrUtil.upperFirst(StrUtil.toCamelCase(tableInfo.getTableName())));
        tableInfo.setTableComment(rs.getString(dbQuery.tableComment()));
        tableInfo.setDatasourceId(id);
        return tableInfo;
    }

}
