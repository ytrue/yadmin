package com.ytrue.yadmin.security.error;


import com.ytrue.yadmin.exeption.code.ExceptionCode;
import com.ytrue.yadmin.util.R;
import com.ytrue.yadmin.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : zhengqing
 * @description 登录过后的权限处理 【注：要和未登录时的权限处理区分开哦~】，认证url权限 - 登录后访问接口无权限 - 自定义403无权限响应内容
 * @date : 2020/10/14 18:52
 */
@Component
@Slf4j
public class YadminAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) {
     //   throw new AccessDeniedException("权限不足");
        ResponseUtil.renderJson(response,
                R.fail(ExceptionCode.NOT_PERMISSION.getCode(), ExceptionCode.NOT_PERMISSION.getMessage()));
    }

}
