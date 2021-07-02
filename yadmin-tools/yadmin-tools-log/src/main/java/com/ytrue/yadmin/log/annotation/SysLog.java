package com.ytrue.yadmin.log.annotation;

import java.lang.annotation.*;

/**
 * @author ytrue
 * @date 2021/7/1 13:39
 * @description 操作日志注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    /**
     * 描述
     *
     * @return {String}
     */
    String value();

    /**
     * 记录执行参数
     *
     * @return {boolean}
     */
    boolean recordRequestParam() default true;

    /**
     * 记录返回参数
     *
     * @return {boolean}
     */
    boolean recordResponseParam() default true;
}
