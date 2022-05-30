package com.ytrue.yadmin.modules.system.controller;

import com.ytrue.yadmin.core.utils.ApiResultResponse;
import com.ytrue.yadmin.tools.security.service.LoginService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ytrue
 * @date 2022/4/13 15:48
 * @description SysUserController
 */
@Api(tags = "系统用户")
@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class SysUserController {


    private final StringRedisTemplate stringRedisTemplate;

    private final LoginService loginService;


    @RequestMapping(value = "login")
    public Object login(
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "email", required = false) String email) {

//        AdminUser user = new AdminUser();
//        user.setId(1)
//                .setUsername(username)
//                .setPassword(password)
//                .setEmail(email);


        return loginService.login();
    }

    @GetMapping("logout")
    public ApiResultResponse<Object> logout() {
        loginService.logout(String.valueOf(1));
        return ApiResultResponse.success();
    }


}
