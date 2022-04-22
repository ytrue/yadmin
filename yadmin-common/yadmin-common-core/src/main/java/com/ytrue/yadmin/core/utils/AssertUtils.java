package com.ytrue.yadmin.core.utils;

import com.ytrue.yadmin.core.enums.ResponseCode;
import com.ytrue.yadmin.core.excptions.AssertInvalidArgumentException;

import java.util.Objects;

/**
 * @author ytrue
 * @date 2022/4/21 16:44
 * @description 断言工具
 */
public class AssertUtils {


    /**
     * 断言对象不为空
     *
     * @param object
     * @param responseCode
     */
    public static void notNull(Object object, ResponseCode responseCode) {
        if (Objects.isNull(object)) {
            reportInvalidArgument(responseCode);
        }
    }

    /**
     * 断言对象为空
     *
     * @param object
     * @param responseCode
     */
    public static void isNull(Object object, ResponseCode responseCode) {
        if (Objects.nonNull(object)) {
            reportInvalidArgument(responseCode);
        }
    }

    /**
     * 报告无效参数
     *
     * @param responseCode
     */
    protected static void reportInvalidArgument(ResponseCode responseCode) {
        throw new AssertInvalidArgumentException(responseCode);
    }
}
