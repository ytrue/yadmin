package com.ytrue.yadmin.security.controller;


import lombok.AllArgsConstructor;
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
@Slf4j
@RestController
@RequestMapping("oauth")

@AllArgsConstructor
public class CustomOauth2Controller {

    private final TokenEndpoint tokenEndpoint;

    /**
     * get方法的
     *
     * @param principal
     * @param map
     * @return
     * @throws HttpRequestMethodNotSupportedException
     */
    @GetMapping("token")
    public OAuth2AccessToken getAccessToken(
            Principal principal,
            @RequestParam Map<String, String> map
    ) throws HttpRequestMethodNotSupportedException {
        return tokenEndpoint.getAccessToken(principal, map).getBody();
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
    public OAuth2AccessToken postAccessToken(
            Principal principal,
            @RequestParam Map<String, String> map
    ) throws HttpRequestMethodNotSupportedException {
        return tokenEndpoint.postAccessToken(principal, map).getBody();
    }
}
