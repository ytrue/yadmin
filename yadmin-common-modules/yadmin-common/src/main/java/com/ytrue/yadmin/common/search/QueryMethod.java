package com.ytrue.yadmin.common.search;

/**
 * @author ytrue
 * @date 2021/4/20 13:35
 * @description 条件枚举
 */
public enum QueryMethod {

    /**
     * ==
     */
    eq,

    /**
     * like
     */
    like,

    /**
     * 日期范围查询 格式 2021-06-05 00:00:00,2021-07-31 11:59:59
     */
    betweenDate
}
