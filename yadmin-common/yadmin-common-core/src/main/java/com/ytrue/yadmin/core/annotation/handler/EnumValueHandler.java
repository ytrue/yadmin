package com.ytrue.yadmin.core.annotation.handler;

import com.ytrue.yadmin.core.annotation.EnumValue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author ytrue
 * @date 2022/6/1 14:39
 * @description EnumValueHandler
 */
public class EnumValueHandler implements ConstraintValidator<EnumValue, Object> {


    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        //TODO 待完善
        return false;
    }
}
