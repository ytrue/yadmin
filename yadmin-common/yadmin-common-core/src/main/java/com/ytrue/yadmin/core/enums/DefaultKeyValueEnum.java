package com.ytrue.yadmin.core.enums;

/**
 * @author ytrue
 * @date 2022/5/29 11:14
 * @description DefaultKeyValueEnum
 */
public enum DefaultKeyValueEnum implements KeyValueEnum<Integer, String> {
    ;


    @Override
    public Integer getKey() {
        return 0;
    }

    @Override
    public String getValue() {
        return null;
    }
}
