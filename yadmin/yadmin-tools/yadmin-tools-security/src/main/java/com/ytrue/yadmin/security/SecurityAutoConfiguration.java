package com.ytrue.yadmin.security;

import com.ytrue.yadmin.security.properties.JwtProperties;
import com.ytrue.yadmin.security.properties.SecurityProperties;
import com.ytrue.yadmin.security.util.JwtUtil;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author ytrue
 * @date 2021/7/3 21:31
 * @description SecurityAutoConfig
 */
@EnableConfigurationProperties({
        SecurityProperties.class,
        JwtProperties.class,
})
public class SecurityAutoConfiguration {

    @Bean
    public JwtUtil jwtUtils(JwtProperties jwtProperties) {
        return new JwtUtil(jwtProperties);
    }
}
