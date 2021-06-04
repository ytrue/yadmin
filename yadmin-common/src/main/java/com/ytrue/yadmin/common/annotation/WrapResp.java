package com.ytrue.yadmin.common.annotation;

/**
 * @author ytrue
 * @date 2021/4/19 09:20
 * @description 包装响应
 */
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WrapResp {
}

