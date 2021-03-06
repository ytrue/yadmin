package com.ytrue.yadmin.core.annotation;

import com.ytrue.yadmin.core.enums.DefaultKeyValueEnum;
import com.ytrue.yadmin.core.enums.KeyValueEnum;

import java.lang.annotation.*;

/**
 * @author ytrue
 * @date 2022/5/28 22:14
 * @description ApiModelEnumProperty
 */
@Target({ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiModelEnumProperty {

    /**
     * 字段名
     */
    String value();

    /**
     * 枚举类
     */
    Class<? extends Enum<? extends KeyValueEnum>> enumClass() default DefaultKeyValueEnum.class;

    /**
     * 可选枚举值，即swagger只会显示该指定值对应的枚举描述，支持String, Integer
     */
    String[] values() default {};

    boolean required() default false;
}
