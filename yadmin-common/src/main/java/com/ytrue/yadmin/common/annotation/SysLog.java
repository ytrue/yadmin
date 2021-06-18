package com.ytrue.yadmin.common.annotation;

import java.lang.annotation.*;

/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 日志操作注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

	String value() default "";
}
