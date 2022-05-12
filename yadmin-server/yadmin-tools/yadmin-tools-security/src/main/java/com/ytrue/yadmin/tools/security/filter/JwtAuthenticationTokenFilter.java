package com.ytrue.yadmin.tools.security.filter;

import com.ytrue.yadmin.tools.security.properties.JwtProperties;
import com.ytrue.yadmin.tools.security.properties.SecurityProperties;
import com.ytrue.yadmin.tools.security.user.LoginUser;
import com.ytrue.yadmin.tools.security.user.User;
import com.ytrue.yadmin.tools.security.utils.JwtOperation;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ytrue
 * @date 2022/5/5 10:59
 * @description 自定义一个过滤器，这个过滤器会去获取请求头中的token，对token进行解析取出其中的userId
 */
@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(SecurityProperties.class)
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final RedisTemplate<String, Object> redisTemplate;

    private final JwtOperation jwtOperation;

    private final SecurityProperties securityProperties;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String token = request.getHeader(securityProperties.getAuthorizationHeaderParameterName());

        //如果header没有token就是直接放行
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        String userId;
        try {
            Jws<Claims> claims = jwtOperation.parseToken(token);
            userId = claims.getBody().getSubject();
        } catch (Exception e) {
            throw new RuntimeException("token非法");
        }


        User user = (User) redisTemplate.opsForValue().get(userId);

        LoginUser loginUser = new LoginUser();
        loginUser.setUser(user);


        //TODO 获取权限信息封装到Authentication中
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, null);

        //存入SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(request, response);
    }
}
