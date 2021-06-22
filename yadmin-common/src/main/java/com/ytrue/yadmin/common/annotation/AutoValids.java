package com.ytrue.yadmin.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author ytrue
 * @date 2021/4/19 11:19
 * @description 批量验证
 */
@Documented
@Retention(RUNTIME)
@Target({METHOD, CONSTRUCTOR})
public @interface AutoValids {

    AutoValid[] value();
}