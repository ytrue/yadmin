package com.ytrue.yadmin.core.utils.enums;

/**
 * @author ytrue
 * @date 2022/5/28 19:15
 * @description NameEnum
 */
public interface NameValueEnum<N, V> {
    /**
     * 获取枚举名称
     *
     * @return 枚举名
     */
    N getName();

    /**
     * 获取枚举值
     *
     * @return 枚举值
     */
    V getValue();
}
