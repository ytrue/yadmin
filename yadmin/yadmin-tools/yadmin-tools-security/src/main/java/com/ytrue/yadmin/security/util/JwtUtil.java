package com.ytrue.yadmin.security.util;

import cn.hutool.core.convert.Convert;
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
public class JwtUtil {


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


    /**
     * 返回token的user_id
     *
     * @param token
     * @return
     */
    public Long getUserIdFromToken(String token) {
        return Convert.toLong(getClaimsFromToken(token).get("user_id"));
    }

}
