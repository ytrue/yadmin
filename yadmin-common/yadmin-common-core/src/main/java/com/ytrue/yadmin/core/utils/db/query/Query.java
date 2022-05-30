package com.ytrue.yadmin.core.utils.db.query;

/**
 * @author ytrue
 * @date 2022/5/27 14:46
 * @description Query
 */
public interface Query {

    /**
     * 表信息查询 SQL
     *
     * @param tableName
     * @return
     */
    String tablesSql(String tableName);


    /**
     * 表字段信息查询 SQL
     *
     * @return
     */
    String tableFieldsSql();

    /**
     * 表名称
     *
     * @return
     */
    String tableName();

    /**
     * 表注释
     *
     * @return
     */
    String tableComment();


    /**
     * 字段名称
     *
     * @return
     */
    String fieldName();


    /**
     * 字段类型
     *
     * @return
     */
    String fieldType();


    /**
     * 字段注释
     *
     * @return
     */
    String fieldComment();

    /**
     * 主键字段
     *
     * @return
     */
    String fieldKey();
}
