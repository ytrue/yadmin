package com.ytrue.yadmin.security.utils;

import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * @author ytrue
 * @date 2022/4/26 10:48
 * @description JwtUtil
 */

public class JwtUtil {

    /**
     * Token过期时间必须大于生效时间
     */
    private static final Long TOKEN_EXPIRE_TIME = 300 * 60 * 1000L;

    /**
     * Token加密解密的密码
     */
    private static final String TOKEN_SECRET = "pwd";

    /**
     * 加密类型 三个值可取 HS256  HS384  HS512
     */
    private static final SignatureAlgorithm JWT_ALG = SignatureAlgorithm.HS256;

    /**
     * 添加一个前缀
     */
    private static final String JWT_SEPARATOR = "Bearer#";

    /**
     * token生效时间(默认是从当前开始生效)
     * 默认：new Date(System.currentTimeMillis() + START_TIME)
     */
    private static final Long START_TIME = 0L;

    /**
     * token在什么时间之前是不可用的（默认从当前时间）
     * 默认：new Date(System.currentTimeMillis() + BEFORE_TIME)
     */
    private static final Long BEFORE_TIME = 0L;


    /**
     * 生产key
     *
     * @return
     */
    private static Key generateKey() {
        // 将将密码转换为字节数组
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] bytes = decoder.decode(TOKEN_SECRET);
        // 根据指定的加密方式，生成密钥
        return new SecretKeySpec(bytes, JWT_ALG.getJcaName());

    }

    /**
     * 创建token
     *
     * @param sub token所面向的用户
     * @param aud 接收token的一方
     * @param jti token的唯一身份标识，主要用来作为一次性token,从而回避重放攻击
     * @param iss token签发者
     * @param map 自定义信息的存储
     * @return 加密后的token字符串
     */


    public static String createToken(String sub, String aud, String jti, String iss, Map<String, Object> map) {
        final JwtBuilder builder = Jwts.builder();

        if (Objects.nonNull(map)) {
            if (!map.isEmpty()) {
                builder.setClaims(map);

            }
        }

        String token = builder
                .signWith(JWT_ALG, generateKey())
                .setSubject(sub)
                .setAudience(aud)
                .setId(jti)
                .setIssuer(iss)
                .setNotBefore(new Date(System.currentTimeMillis() + BEFORE_TIME))
                .setIssuedAt(new Date(System.currentTimeMillis() + START_TIME))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRE_TIME))
                .compact();
        return JWT_SEPARATOR + token;

    }

    /**
     * 创建token
     *
     * @param sub token所面向的用户
     * @param aud 接收token的一方
     * @param map 自定义信息存储
     * @return token 字符串
     */
    public static String createToken(String sub, String aud, Map<String, Object> map) {
        return createToken(sub, aud, new Date().toString(), null, map);

    }

    /**
     * 创建token
     *
     * @param sub token所面向的用户
     * @param map 自定义信息存储
     * @return token字符串
     */
    public static String createToken(String sub, Map<String, Object> map) {
        return createToken(sub, null, map);

    }

    /**
     * 创建token
     *
     * @param sub token所面向的用户
     * @return token字符串
     */


    public static String createToken(String sub) {
        return createToken(sub, null);

    }


    /**
     * 解析token
     * 可根据Jws<Claims>   获取  header|body|getSignature三部分数据
     *
     * @param token token字符串
     * @return Jws
     */
    public static Jws<Claims> parseToken(String token) {
        // 移除 token 前的"Bearer#"字符串
        int pos = token.indexOf(JWT_SEPARATOR);
        token = pos == -1 ? "" : token.substring(pos + JWT_SEPARATOR.length());

        // 解析 token 字符串
        return Jwts.parser().setSigningKey(generateKey()).parseClaimsJws(token);
    }

    /**
     * 校验token,校验是否是本服务器的token
     *
     * @param token token字符串
     * @return boolean
     */
    public static Boolean checkToken(String token) {
        return parseToken(token).getBody() != null;
    }

    /**
     * 根据sub判断token
     *
     * @param token token字符串
     * @param sub   面向的用户
     * @return boolean
     */
    public static Boolean checkToken(String token, String sub) {
        return parseToken(token).getBody().getSubject().equals(sub);
    }
}
