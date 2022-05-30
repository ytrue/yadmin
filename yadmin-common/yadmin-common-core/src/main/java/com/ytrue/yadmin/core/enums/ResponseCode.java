package com.ytrue.yadmin.core.enums;

import com.ytrue.yadmin.core.base.BaseExceptionCode;

/**
 * @author ytrue
 * @date 2022/4/21 17:05
 * @description 响应状态枚举
 * <p>
 * 枚举的值都是固定的，且是全局唯一的，
 * 用 java 的术语来说就是单例的，
 * 所以我们写枚举类时一般都不会给自定义属性写Setter方法。
 * 本着单例思想，我们还是加上final。
 */
public enum ResponseCode implements BaseExceptionCode {


    //状态码
    SUCCESS(2000, "成功"),
    FAIL(5000, "服务器内部错误"),
    DATA_NOT_FOUND(5004, "数据不存在"),
    UNKNOWN(50001, "未知异常");

    /**
     * code错误码
     */
    private final Integer code;

    /**
     * 前端进行页面展示的信息
     */
    private final String message;

    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
