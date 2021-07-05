package com.ytrue.yadmin.common.aspect;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.ytrue.yadmin.common.annotation.AutoValid;
import com.ytrue.yadmin.common.annotation.AutoValids;
import com.ytrue.yadmin.common.exeption.AutoValidException;
import com.ytrue.yadmin.common.utils.GsonUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


/**
 * @author ytrue
 * @date 2021/4/19 11:21
 * @description 验证注解切面类
 */
@Order
@Aspect
@Component
@Slf4j
@AllArgsConstructor
public class AutoValidAspect {

    /**
     * 空
     */
    private static final String EMPTY_STRING = "{}";


    /**
     * 批量AutoValid 验证
     *
     * @param point
     * @param valids
     * @return
     * @throws Throwable
     */
    @Before(value = "@annotation(valids)")
    public void aroundValids(JoinPoint point, AutoValids valids) throws Throwable {
        Set<ConstraintViolation<Object>> violationSet = new HashSet<>();
        for (AutoValid valid : valids.value()) {
            Set<ConstraintViolation<Object>> set = verification(point, valid);
            violationSet.addAll(set);
        }
        result(point, violationSet);
    }


    /**
     * 单个AutoValid 切面
     *
     * @param point
     * @param valid
     * @throws Throwable
     */
    @Before(value = "@annotation(valid)")
    public void before(JoinPoint point, AutoValid valid) throws Throwable {
        Set<ConstraintViolation<Object>> violationSet = verification(point, valid);
        result(point, violationSet);
    }


    /**
     * 验证
     *
     * @param point
     * @param valid
     * @return
     */
    private Set<ConstraintViolation<Object>> verification(JoinPoint point, AutoValid valid) {
        Set<ConstraintViolation<Object>> violationSet = null;
        //判断是否为空，并且第一个次数不能为null
        if (ArrayUtil.isNotEmpty(point.getArgs()) && point.getArgs()[0] != null) {
            Object object = GsonUtils.from(getParams(point, valid), valid.entity());

            if (ArrayUtil.isEmpty(valid.groups())) {
                violationSet = Validation.buildDefaultValidatorFactory().getValidator().validate(object);
            } else {
                violationSet = Validation.buildDefaultValidatorFactory().getValidator().validate(object, valid.groups());
            }
        }
        return violationSet;
    }

    /**
     * 结果
     *
     * @param point
     * @param violationSet
     * @return
     * @throws Throwable
     */
    private void result(JoinPoint point, Set<ConstraintViolation<Object>> violationSet) throws Throwable {
        if (!CollUtil.isEmpty(violationSet)) {
            ArrayList<String> errorList = new ArrayList<>();
            //循环错误结果
            violationSet.forEach(objectConstraintViolation -> {
                errorList.add(objectConstraintViolation.getMessage());
            });
            //这里要抛异，不能往下走

            throw new AutoValidException(GsonUtils.to(errorList));
        }
    }

    /**
     * 获得参数,这个返回的是一个json数据
     *
     * @param point
     * @param valid
     * @return
     */
    private String getParams(JoinPoint point, AutoValid valid) {

        if (StrUtil.isBlank(valid.key())) {
            return GsonUtils.to(point.getArgs()[0]);
        } else {
            //这里是判断一个属性是否存在
            String value = GsonUtils.getAsString(GsonUtils.to(point.getArgs()[0]), valid.key());
            if (!StrUtil.hasEmpty(value)) {
                return value;
            }
        }
        return EMPTY_STRING;
    }
}
