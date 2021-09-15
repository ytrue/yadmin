package com.ytrue.yadmin.security.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ytrue
 * @date 2021/2/28 12:43
 * @description 自定义token返回格式
 */
@RestController
@RequestMapping("oauth")
@Slf4j
public class CustomOauth2Controller {

    @Autowired
    private TokenEndpoint tokenEndpoint;

    /**
     * get方法的
     *
     * @param principal
     * @param map
     * @return
     * @throws HttpRequestMethodNotSupportedException
     */
    @GetMapping("token")
    public HashMap<String, Object> getAccessToken(
            Principal principal,
            @RequestParam Map<String, String> map
    ) throws HttpRequestMethodNotSupportedException {
        return custom(tokenEndpoint.getAccessToken(principal, map).getBody());
    }

    /**
     * post方法的
     *
     * @param principal
     * @param map
     * @return
     * @throws HttpRequestMethodNotSupportedException
     */
    @PostMapping("token")
    public HashMap<String, Object> postAccessToken(
            Principal principal,
            @RequestParam Map<String, String> map
    ) throws HttpRequestMethodNotSupportedException {
        return custom(tokenEndpoint.postAccessToken(principal, map).getBody());
    }

    /**
     * 自定义返回格式
     *
     * @param accessToken
     * @return
     */
    private HashMap<String, Object> custom(OAuth2AccessToken accessToken) {
        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
        HashMap<String, Object> data = new HashMap<>(16);
        data.put("accessToken", token.getValue());
        if (token.getRefreshToken() != null) {
            data.put("refreshToken", token.getRefreshToken().getValue());
        }
        return data;
    }
}
