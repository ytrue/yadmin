package com.ytrue.yadmin.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ytrue
 * @date 2022/5/27 10:44
 * @description 数据库驱动类型
 */
@Getter
@AllArgsConstructor
public enum DatabaseType {

    //数据库类型
    MySQL("com.mysql.cj.jdbc.Driver"),
    Oracle("oracle.jdbc.driver.OracleDriver"),
    PostgreSQL("org.postgresql.Driver");

    private final String driverClass;

}
