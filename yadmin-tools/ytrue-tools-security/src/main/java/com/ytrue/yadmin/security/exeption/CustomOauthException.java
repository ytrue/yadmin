package com.ytrue.yadmin.security.exeption;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import com.ytrue.yadmin.security.error.CustomOauthExceptionSerializer;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author ytrue
 * @date 2021/2/28 12:47
 * @description 认证服务器错误异常
 */
@JsonSerialize(using = CustomOauthExceptionSerializer.class)
public class CustomOauthException extends OAuth2Exception {
    public CustomOauthException(String msg) {
        super(msg);
    }
}

