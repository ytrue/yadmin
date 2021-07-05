package com.ytrue.yadmin.exeption.code;

/**
 * @author ytrue
 * @date 2021/4/19 09:44
 * @description 响应枚举
 */
public enum ExceptionCode implements BaseExceptionCode {

    /**
     * 成功
     */
    SUCCESS(200, "成功"),

    /**
     * 服务器错误
     */
    EXCEPTION(500, "服务器错误"),

    /**
     * 错误请求
     */
    BAD_REQUEST(900, "错误请求"),


    /**
     * 没有登录
     */
    NOT_LOGIN(903, "未登录"),

    /**
     * 权限不足
     */
    NOT_PERMISSION(403, "权限不足");

    /**
     * code错误码
     */
    private Integer code;

    /**
     * 前端进行页面展示的信息
     */
    private String message;

    ExceptionCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
