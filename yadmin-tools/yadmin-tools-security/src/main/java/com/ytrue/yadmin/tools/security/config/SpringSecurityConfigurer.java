package com.ytrue.yadmin.tools.security.config;

import com.ytrue.yadmin.tools.security.filter.JwtAuthenticationTokenFilter;
import com.ytrue.yadmin.tools.security.handler.AccessDeniedHandlerImpl;
import com.ytrue.yadmin.tools.security.handler.AuthenticationEntryPointImpl;
import com.ytrue.yadmin.tools.security.integration.IntegrationAuthenticationFilter;
import com.ytrue.yadmin.tools.security.properties.SecurityProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Set;

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

    private final JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    private final SecurityProperties securityProperties;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //关闭csrf
                .csrf().disable()
                //不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        Set<String> ignoreAuth = securityProperties.getIgnoreAuth();
        String[] stringArray = new String[ignoreAuth.size()];
        String[] ignoreAuthArray = ignoreAuth.toArray(stringArray);

        http.authorizeRequests()
                // 对于登录接口 允许匿名访问
                .antMatchers(ignoreAuthArray).anonymous()
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
        http.addFilterBefore(jwtAuthenticationTokenFilter, IntegrationAuthenticationFilter.class);

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
