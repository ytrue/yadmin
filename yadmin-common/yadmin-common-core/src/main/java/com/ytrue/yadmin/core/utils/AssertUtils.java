package com.ytrue.yadmin.core.utils;

import com.ytrue.yadmin.core.base.BaseExceptionCode;
import com.ytrue.yadmin.core.excptions.AssertInvalidArgumentException;

import java.util.Objects;

/**
 * @author ytrue
 * @date 2022/4/21 16:44
 * @description 断言工具
 */
public class AssertUtils {


    /**
     * 笃定 object 一定不为空
     *
     * @param object
     * @param responseCode
     */
    public static void notNull(Object object, BaseExceptionCode responseCode) {
        if (Objects.isNull(object)) {
            reportInvalidArgument(responseCode);
        }
    }

    /**
     * 笃定 object 一定为空
     *
     * @param object
     * @param responseCode
     */
    public static void isNull(Object object, BaseExceptionCode responseCode) {
        if (Objects.nonNull(object)) {
            reportInvalidArgument(responseCode);
        }
    }


    /**
     * 笃定 字符串value 和 expect 一定相等
     *
     * @param value
     * @param expect
     * @param responseCode
     */
    public static void strEq(String value, String expect, BaseExceptionCode responseCode) {
        if (!value.equals(expect)) {
            reportInvalidArgument(responseCode);
        }
    }

    /**
     * 笃定 value 一定小于 limit
     *
     * @param value
     * @param limit
     * @param responseCode
     */
    public static void lessThanEq(Long value, Long limit, BaseExceptionCode responseCode) {
        if (value > limit) {
            reportInvalidArgument(responseCode);
        }
    }

    /**
     * 报告无效参数
     *
     * @param responseCode
     */
    private static void reportInvalidArgument(BaseExceptionCode responseCode) {
        throw new AssertInvalidArgumentException(responseCode);
    }
}
