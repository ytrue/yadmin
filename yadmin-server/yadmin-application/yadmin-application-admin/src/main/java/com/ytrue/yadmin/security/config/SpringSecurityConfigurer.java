package com.ytrue.yadmin.security.config;

import com.ytrue.yadmin.security.handler.AccessDeniedHandlerImpl;
import com.ytrue.yadmin.security.handler.AuthenticationEntryPointImpl;
import com.ytrue.yadmin.security.integration.IntegrationAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author ytrue
 * @date 2022/4/26 10:36
 * @description SpringSecurityConfigurer
 */
@Configurable
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfigurer extends WebSecurityConfigurerAdapter {

    private final AccessDeniedHandlerImpl accessDeniedHandler;

    private final AuthenticationEntryPointImpl authenticationEntryPoint;

    private final IntegrationAuthenticationFilter integrationAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //关闭csrf
                .csrf().disable()
                //不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 对于登录接口 允许匿名访问
                .antMatchers(

                        "/user/login",
                        "/user/**",
                        "/favicon.ico",
                        "/actuator/**",
                        "/passport/**",
                        //这个是swagger文档的，要开放出来
                        "/v2/api-docs**",
                        "/webjars/**",
                        "/doc.html**",
                        "/swagger**"
                ).anonymous()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();


        //配置异常处理器
        http.exceptionHandling()
                //配置认证失败处理器
                .authenticationEntryPoint(authenticationEntryPoint)
                //配置授权失败处理器
                .accessDeniedHandler(accessDeniedHandler);

        //添加过滤器
        http.addFilterBefore(integrationAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        //允许跨域
        http.cors();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 设置加密的方式
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
