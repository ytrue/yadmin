package com.ytrue.yadmin.modules.security.error;

import com.ytrue.yadmin.modules.security.exeption.CustomOauthException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

/**
 * @author ytrue
 * @date 2021/2/28 12:47
 * @description 自定义认证服务器Web响应异常转换器
 */
@Component("customWebResponseExceptionTranslator")
public class CustomWebResponseExceptionTranslator implements WebResponseExceptionTranslator<OAuth2Exception> {

    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
        OAuth2Exception oAuth2Exception = (OAuth2Exception) e;
        return ResponseEntity
                .status(oAuth2Exception.getHttpErrorCode())
                .body(new CustomOauthException(oAuth2Exception.getMessage()));
    }
}
