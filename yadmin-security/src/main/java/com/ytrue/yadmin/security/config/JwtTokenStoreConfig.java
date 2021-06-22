package com.ytrue.yadmin.security.config;


import com.ytrue.yadmin.security.jwt.CustomJwtAccessTokenConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
/**
 * @author ytrue
 * @date 2021/2/28 12:47
 * @description jwt配置-后期迁移到security模块中
 */
@Configuration
public class JwtTokenStoreConfig {

    @Value("${jwt.signing.key}")
    private String signingKey;

    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new CustomJwtAccessTokenConverter();
        //配置JWT使用的秘钥
        accessTokenConverter.setSigningKey(signingKey);
        return accessTokenConverter;
    }

}
