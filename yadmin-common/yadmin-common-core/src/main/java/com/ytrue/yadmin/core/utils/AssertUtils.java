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
     * 断言对象不为空
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
     * 断言对象为空
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
     * 报告无效参数
     *
     * @param responseCode
     */
    protected static void reportInvalidArgument(BaseExceptionCode responseCode) {
        throw new AssertInvalidArgumentException(responseCode);
    }
}
