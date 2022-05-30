package com.ytrue.yadmin.core.utils.db;


import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author ytrue
 * @date 2022/5/27 10:36
 * @description DB工具类
 */
public class DbUtils {
    /**
     * 连接超时秒数
     */
    private static final int CONNECTION_TIMEOUTS_SECONDS = 30;

    /**
     * 获取连接
     *
     * @param url      地址
     * @param username 账号
     * @param password 密码
     * @param driver   驱动
     * @return
     */
    public static Connection getConnection(String url, String username, String password, String driver) throws Exception {
        //设置超时时间
        DriverManager.setLoginTimeout(CONNECTION_TIMEOUTS_SECONDS);
        Class.forName(driver);
        return DriverManager.getConnection(url, username, password);
    }


}
