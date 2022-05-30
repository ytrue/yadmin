package com.ytrue.yadmin.core.enums;

import com.ytrue.yadmin.core.utils.enums.NameValueEnum;

/**
 * @author ytrue
 * @date 2022/5/29 11:14
 * @description DefaultNameValueEnum
 */
public enum  DefaultNameValueEnum implements NameValueEnum<Integer, String> {
    ;


    @Override
    public Integer getName() {
        return 0;
    }

    @Override
    public String getValue() {
        return null;
    }
}
