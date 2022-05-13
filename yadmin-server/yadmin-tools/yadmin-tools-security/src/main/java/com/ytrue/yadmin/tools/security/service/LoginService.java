package com.ytrue.yadmin.tools.security.service;

import java.util.Map;

/**
 * @author ytrue
 * @date 2022/4/26 11:04
 * @description LoginService
 */
public interface LoginService {

    /**
     * 登录
     *
     * @return
     */
    Map<String, String> login();

    /**
     * 退出登录
     */
    void logout(String userId);
}
