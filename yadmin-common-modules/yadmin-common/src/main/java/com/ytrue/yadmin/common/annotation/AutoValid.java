package com.ytrue.yadmin.common.annotation;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author ytrue
 * @date 2021/4/19 11:18
 * @description 单个验证
 */
@Documented
@Retention(RUNTIME)
@Target({METHOD, CONSTRUCTOR})
@Repeatable(AutoValids.class)
public @interface AutoValid {

    Class<?> entity() default String.class;

    Class<?>[] groups() default {};

    String key() default "";
}
