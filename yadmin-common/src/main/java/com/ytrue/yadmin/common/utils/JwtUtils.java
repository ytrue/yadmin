package com.ytrue.yadmin.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * 要读取配置文件的东西,必须把当前类加入spring的容器管理
 *
 * @author ytrue
 * @date 2021/6/8 23:13
 * @description JwtUtils
 */
@Component
public class JwtUtils {

    @Value("${jwt.signing.key}")
    private String signingKey;


    /**
     * 解析token
     *
     * @param token
     * @return
     */
    public Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(signingKey.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
    }

}
