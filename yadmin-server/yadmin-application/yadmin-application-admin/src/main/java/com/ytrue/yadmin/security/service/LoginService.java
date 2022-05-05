package com.ytrue.yadmin.security.service;

import com.ytrue.yadmin.security.domain.User;

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
     * @param user
     * @return
     */
    Map<String, String> login(User user);

    /**
     * 退出登录
     */
    void logout();
}
