package com.ytrue.yadmin.common.aspect;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ytrue.yadmin.common.annotation.AutoValid;
import com.ytrue.yadmin.common.annotation.AutoValids;
import com.ytrue.yadmin.common.response.ResponseCode1;
import com.ytrue.yadmin.common.response.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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
    @Around(value = "@annotation(valids)")
    public Object aroundValids(ProceedingJoinPoint point, AutoValids valids) throws Throwable {
        try {
            Set<ConstraintViolation<Object>> violationSet = new HashSet<>();

            for (AutoValid valid : valids.value()) {
                Set<ConstraintViolation<Object>> set = verification(point, valid);
                violationSet.addAll(set);
            }
            return result(point, violationSet);
        } catch (Throwable e) {
            e.printStackTrace();

            return ResponseData.fail();
        }
    }


    /**
     * 单个AutoValid 切面
     *
     * @param point
     * @param valid
     * @return
     * @throws Throwable
     */
    @Around(value = "@annotation(valid)")
    public Object aroundValid(ProceedingJoinPoint point, AutoValid valid) throws Throwable {
        try {
            Set<ConstraintViolation<Object>> violationSet = verification(point, valid);
            return result(point, violationSet);
        } catch (Throwable e) {
            e.printStackTrace();
            return ResponseData.fail();
        }
    }


    /**
     * 验证
     *
     * @param point
     * @param valid
     * @return
     */
    private Set<ConstraintViolation<Object>> verification(ProceedingJoinPoint point, AutoValid valid) {
        Set<ConstraintViolation<Object>> violationSet = null;
        //判断是否为空，并且第一个次数不能为null
        if (ArrayUtil.isNotEmpty(point.getArgs()) && point.getArgs()[0] != null) {
            Object object = JSON.parseObject(getParams(point, valid), valid.entity());
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
    private Object result(ProceedingJoinPoint point, Set<ConstraintViolation<Object>> violationSet) throws Throwable {
        if (CollUtil.isEmpty(violationSet)) {
            return point.proceed();
        } else {
            ArrayList<String> errorList = new ArrayList<>();
            //循环错误结果
            violationSet.forEach(objectConstraintViolation -> {
                errorList.add(objectConstraintViolation.getMessage());
            });

            return ResponseData.fail(
                    ResponseCode1.BAD_REQUEST.getCode(),
                    ResponseCode1.BAD_REQUEST.getMessage(),
                    errorList
            );
        }
    }

    /**
     * 获得参数,这个返回的是一个json数据
     *
     * @param point
     * @param valid
     * @return
     */
    private String getParams(ProceedingJoinPoint point, AutoValid valid) {

        if (StrUtil.isBlank(valid.key())) {
            return JSON.toJSONString(point.getArgs()[0]);
        } else {
            JSONObject object = JSON.parseObject(JSON.toJSONString(point.getArgs()[0]));
            if (object.containsKey(valid.key())) {
                return object.getString(valid.key());
            }
        }
        return EMPTY_STRING;
    }
}
