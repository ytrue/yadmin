package com.ytrue.yadmin.core.annotation;

import com.ytrue.yadmin.core.annotation.handler.EnumValueHandler;
import com.ytrue.yadmin.core.enums.DefaultKeyValueEnum;
import com.ytrue.yadmin.core.enums.KeyValueEnum;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author ytrue
 * @date 2022/6/1 14:38
 * @description 枚举值校验，即值只能是指定枚举类中的value值
 */
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EnumValueHandler.class)
public @interface EnumValue {

    /**
     * 枚举类(必须实现{@link KeyValueEnum}接口的枚举)
     */
    Class<? extends Enum<? extends KeyValueEnum>> value() default DefaultKeyValueEnum.class;

    /**
     * 可选枚举值
     */
    String[] values() default {};

    /**
     * 字段名,如果字段名称为空，默认为枚举类去掉Enum标志且首字母小写（如：TestTypeEnum -> testType）
     */
    String name() default "";

    /**
     * 错误提示
     */
    String message() default "{name}枚举值错误，可选值为{enums}";

    /**
     * 用于分组校验
     */
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
