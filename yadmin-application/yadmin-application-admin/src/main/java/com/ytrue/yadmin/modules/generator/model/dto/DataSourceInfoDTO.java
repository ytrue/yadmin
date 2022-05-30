package com.ytrue.yadmin.modules.generator.model.dto;

import com.ytrue.yadmin.core.enums.DatabaseType;
import com.ytrue.yadmin.core.utils.db.DbUtils;
import com.ytrue.yadmin.core.utils.db.query.MySqlQuery;
import com.ytrue.yadmin.core.utils.db.query.OracleQuery;
import com.ytrue.yadmin.core.utils.db.query.PostgreSqlQuery;
import com.ytrue.yadmin.core.utils.db.query.Query;
import com.ytrue.yadmin.modules.generator.model.GenDataSource;
import lombok.Data;

import java.sql.Connection;
import java.util.HashMap;

/**
 * @author ytrue
 * @date 2022/5/27 14:59
 * @description 数据源信息
 */
@Data
public class DataSourceInfoDTO {

    private static final HashMap<DatabaseType, Query> MAP = new HashMap<>(3);

    static {
        MAP.put(DatabaseType.MySQL, new MySqlQuery());
        MAP.put(DatabaseType.Oracle, new OracleQuery());
        MAP.put(DatabaseType.PostgreSQL, new PostgreSqlQuery());
    }

    /**
     * 数据源ID
     */
    private Long id;

    /**
     * 数据库类型
     */
    private DatabaseType databaseType;

    /**
     * 数据库URL
     */
    private String connUrl;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    /**
     * 查询
     */
    private Query dbQuery;

    /**
     * 连接
     */
    private Connection connection;


    public DataSourceInfoDTO(GenDataSource dataSource) throws Exception {
        this.id = dataSource.getId();
        this.databaseType = DatabaseType.valueOf(dataSource.getDbType());
        this.connUrl = dataSource.getConnUrl();
        this.username = dataSource.getUsername();
        this.password = dataSource.getPassword();
        this.dbQuery = getQuery(this.databaseType);
        this.connection = DbUtils.getConnection(this.connUrl, this.username, this.password, this.databaseType.getDriverClass());
    }


    public DataSourceInfoDTO(Connection connection) throws Exception {
        this.id = 0L;
        this.databaseType = DatabaseType.valueOf(connection.getMetaData().getDatabaseProductName());
        this.dbQuery = getQuery(this.databaseType);
        this.connection = connection;
    }

    private Query getQuery(DatabaseType type) {
        if (MAP.containsKey(type)) {
            return MAP.get(type);
        }
        return null;
    }
}
