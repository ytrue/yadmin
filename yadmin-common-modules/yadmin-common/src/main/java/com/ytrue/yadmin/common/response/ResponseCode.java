package com.ytrue.yadmin.common.response;

import lombok.Getter;

/**
 * @author ytrue
 * @date 2021/4/19 09:44
 * @description 响应枚举
 */
@Getter
public enum ResponseCode {

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

    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
