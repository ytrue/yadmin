package com.ytrue.yadmin.tools.security.service.impl;

import com.ytrue.yadmin.tools.security.service.LoginService;
import com.ytrue.yadmin.tools.security.user.LoginUser;
import com.ytrue.yadmin.tools.security.user.User;
import com.ytrue.yadmin.tools.security.utils.JwtOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    private final RedisTemplate<String, Object> redisTemplate;

    private final JwtOperation jwtOperation;

    @Override
    public Map<String, String> login() {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(null, null);

        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        LoginUser loginUser = ((LoginUser) authenticate.getPrincipal());
        User user1 = loginUser.getUser();
        String userId = user1.userId();

        //加入缓存,存放到hash里面去
        redisTemplate.opsForValue().set(userId, user1);

        //把token响应给前端
        HashMap<String, String> map = new HashMap<>(16);
        map.put("token", jwtOperation.createToken(userId));

        return map;
    }

    /**
     * 退出登录
     */
    @Override
    public void logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getPrincipal();

    }
}
