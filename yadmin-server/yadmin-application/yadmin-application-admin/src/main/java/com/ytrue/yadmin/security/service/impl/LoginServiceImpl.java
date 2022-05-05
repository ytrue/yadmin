package com.ytrue.yadmin.security.service.impl;

import com.ytrue.yadmin.security.domain.LoginUser;
import com.ytrue.yadmin.security.domain.User;
import com.ytrue.yadmin.security.service.LoginService;
import com.ytrue.yadmin.security.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author ytrue
 * @date 2022/4/26 11:04
 * @description LoginServiceImpl
 */
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authenticationManager;


    @Override
    public Map<String, String> login(User user) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误");
        }

        //使用userId生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJwt(userId);

        //把token响应给前端
        HashMap<String, String> map = new HashMap<>(16);
        map.put("token", jwt);

        return map;
    }

    /**
     * 退出登录
     */
    @Override
    public void logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       authentication.getPrincipal();

        System.out.println(  authentication.getPrincipal());
    }
}
