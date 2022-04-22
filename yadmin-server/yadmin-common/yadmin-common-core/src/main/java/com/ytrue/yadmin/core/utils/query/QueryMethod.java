package com.ytrue.yadmin.core.utils.query;

/**
 * @author ytrue
 * @date 2022/4/20 16:07
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
     * !=
     */
    ne,

    /**
     * 日期范围查询 格式 2021-06-05 00:00:00,2021-07-31 11:59:59
     */
    betweenDate
}
