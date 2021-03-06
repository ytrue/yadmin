package com.ytrue.yadmin.modules.generator.service.manager;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.ytrue.yadmin.core.enums.ResponseCode;
import com.ytrue.yadmin.core.utils.AssertUtils;
import com.ytrue.yadmin.core.utils.db.query.Query;
import com.ytrue.yadmin.modules.generator.dao.GenDataSourceDao;
import com.ytrue.yadmin.modules.generator.model.GenDataSource;
import com.ytrue.yadmin.modules.generator.model.GenTableField;
import com.ytrue.yadmin.modules.generator.model.GenTableInfo;
import com.ytrue.yadmin.modules.generator.model.dto.DataSourceInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
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
    public GenTableInfo getDataSourceTable(Long id, String tableName) {
        DataSourceInfoDTO dataSourceInfo = getDataSourceInfo(id);

        GenTableInfo data = null;

        Query dbQuery = dataSourceInfo.getDbQuery();
        @Cleanup Connection connection = dataSourceInfo.getConnection();
        //查询数据
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(dbQuery.tablesSql(tableName));
        ResultSet rs = preparedStatement.executeQuery();
        //填充
        if (rs.next()) {
            data = createTableInfo(rs, dbQuery, id);
        }

        //关闭
        dataSourceInfo.getConnection().close();
        return data;
    }


    /**
     * 获取数据源所有的表信息
     *
     * @param id
     * @return
     */
    @SneakyThrows
    public List<GenTableInfo> getDataSourceTables(Long id) {
        DataSourceInfoDTO dataSourceInfo = getDataSourceInfo(id);

        List<GenTableInfo> tableInfoList = new ArrayList<>();

        Query dbQuery = dataSourceInfo.getDbQuery();
        @Cleanup Connection connection = dataSourceInfo.getConnection();
        //查询数据
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(dbQuery.tablesSql(null));
        ResultSet rs = preparedStatement.executeQuery();
        //填充
        while (rs.next()) {
            GenTableInfo tableInfo = createTableInfo(rs, dbQuery, id);
            tableInfoList.add(tableInfo);
        }
        //关闭
        dataSourceInfo.getConnection().close();
        return tableInfoList;
    }

    /**
     * 获取表的列属性
     *
     * @param databaseId
     * @param tableId
     * @param tableName
     * @return
     */
    @SneakyThrows
    public List<GenTableField> getTableColumns(Long databaseId, Long tableId, String tableName) {

        DataSourceInfoDTO info = getDataSourceInfo(databaseId);

        List<GenTableField> tableFieldList = new ArrayList<>();

        Query dbQuery = info.getDbQuery();
        String tableFieldsSql = dbQuery.tableFieldsSql();

        @Cleanup Connection connection = info.getConnection();
        //目前只处理mysql
        tableFieldsSql = String.format(tableFieldsSql, tableName);
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(tableFieldsSql);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            GenTableField field = new GenTableField();

            field.setTableId(tableId);
            field.setTableName(tableName);
            field.setColumnName(rs.getString(dbQuery.fieldName()));

            String columnType = rs.getString(dbQuery.fieldType());
            if (columnType.contains(" ")) {
                columnType = columnType.substring(0, columnType.indexOf(" "));
            }
            field.setColumnType(columnType);
            field.setColumnComment(rs.getString(dbQuery.fieldComment()));
            String key = rs.getString(dbQuery.fieldKey());
            field.setIsPk(StringUtils.isNotBlank(key) && "PRI".equalsIgnoreCase(key));

            tableFieldList.add(field);
        }
        //关闭
        return tableFieldList;
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
    private GenTableInfo createTableInfo(ResultSet rs, Query dbQuery, Long id) throws Exception {
        GenTableInfo tableInfo = new GenTableInfo();
        tableInfo.setTableName(rs.getString(dbQuery.tableName()));
        //下划线转小驼峰，首字符大写
        tableInfo.setClassName(StrUtil.upperFirst(StrUtil.toCamelCase(tableInfo.getTableName())));
        tableInfo.setTableComment(rs.getString(dbQuery.tableComment()));
        tableInfo.setDatasourceId(id);
        return tableInfo;
    }

}
