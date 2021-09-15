package com.ytrue.yadmin.security.error;

import com.ytrue.yadmin.security.exeption.CustomOauthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

/**
 * @author ytrue
 * @date 2021/2/28 12:47
 * @description 自定义认证服务器Web响应异常转换器, 获取access_token失败返回的数据格式
 */
@Slf4j
@Component("yadminWebResponseExceptionTranslator")
public class YadminWebResponseExceptionTranslator implements WebResponseExceptionTranslator<OAuth2Exception> {

    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
        log.error("YadminWebResponseExceptionTranslator 我被触发了" + e.getMessage());
        OAuth2Exception oAuth2Exception = (OAuth2Exception) e;
        return ResponseEntity
                .status(oAuth2Exception.getHttpErrorCode())
                .body(new CustomOauthException(oAuth2Exception.getMessage()));
    }
}
