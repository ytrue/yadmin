package com.ytrue.yadmin.security.utils;

import com.ytrue.yadmin.security.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;

import java.nio.charset.StandardCharsets;

/**
 * @author ytrue
 * @date 2021/7/3 21:31
 * @description JwtUtils
 */
@AllArgsConstructor
public class JwtUtils {


    private final JwtProperties jwtProperties;

    /**
     * 解析token
     *
     * @param token
     * @return
     */
    public Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSigningKey().getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
    }
}
