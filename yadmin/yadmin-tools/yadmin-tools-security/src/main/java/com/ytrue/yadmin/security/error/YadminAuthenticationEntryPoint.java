package com.ytrue.yadmin.security.error;


import com.ytrue.yadmin.exeption.code.ExceptionCode;
import com.ytrue.yadmin.utils.R;
import com.ytrue.yadmin.utils.ResponseUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 资源服务器-未登录访问权限控制
 *
 * @author yangyi
 * @date 2020/5/16 17:58
 * @description 这里是认证权限入口 -> 即在未登录的情况下访问所有接口都会拦截到此（除了放行忽略接口）
 * 没有登录触发这个类
 */
@Component
@Slf4j
public class YadminAuthenticationEntryPoint implements AuthenticationEntryPoint {


    @Override
    @SneakyThrows
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        // throw new AuthenticationCredentialsNotFoundException("未登录");
        ResponseUtils.renderJson(response,
                R.fail(ExceptionCode.NOT_LOGIN.getCode(), ExceptionCode.NOT_LOGIN.getMessage()));
    }
}
