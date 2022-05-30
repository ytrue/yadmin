package com.ytrue.yadmin.modules.generator.model.vo;

import com.ytrue.yadmin.core.utils.enums.NameValueEnum;

/**
 * @author ytrue
 * @date 2022/5/28 22:39
 * @description TypeEnum
 */
public enum TypeEnum implements NameValueEnum<String, String> {
    /**
     * 类型1
     */
    T1("1", "类型1"),

    T2("2", "类型2"),

    T3("3", "类型3");

    private final String name;
    private final String value;

    TypeEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
