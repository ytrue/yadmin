package com.ytrue.yadmin.security;


import com.ytrue.yadmin.security.error.CustomAccessDeniedHandler;
import com.ytrue.yadmin.security.error.CustomAuthenticationEntryPoint;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 资源服务器配置
 */
@Configuration
@AllArgsConstructor
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {


    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;


    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    /**
     * 配置
     *
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .requestMatchers().anyRequest()
                .and()
                .anonymous()
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/upload/**",
                        "/favicon.ico",
                        "/actuator/**",
                        "/svg/**",
                        "/passport/**",
                        //这个是swagger文档的，要开放出来
                        "/webjars/**",
                        "/doc.html**",
                        "/swagger**"
                ).permitAll()
                .and()
                .cors() //资源服务解决跨域，需要添加此配置项
                .and()
                .authorizeRequests()
                //配置所有访问控制，必须认证过后才可以访问
                .antMatchers("/**").authenticated();
    }

    /**
     * 定制返回错误信息-- 未登录访问权限控制和登录过后的权限不足
     *
     * @param resources
     * @throws Exception
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.authenticationEntryPoint(customAuthenticationEntryPoint)
                .accessDeniedHandler(customAccessDeniedHandler);
    }


}
