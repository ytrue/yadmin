package com.ytrue.yadmin.core.enums;

/**
 * @author ytrue
 * @date 2022/5/28 19:15
 * @description NameEnum
 */
public interface KeyValueEnum<K, V> {
    /**
     * 获取枚举名称
     *
     * @return 枚举名
     */
    K getKey();

    /**
     * 获取枚举值
     *
     * @return 枚举值
     */
    V getValue();
}
