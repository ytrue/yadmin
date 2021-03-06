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
                //??????csrf
                .csrf().disable()
                //?????????Session??????SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        Set<String> ignoreAuth = securityProperties.getIgnoreAuth();
        String[] stringArray = new String[ignoreAuth.size()];
        String[] ignoreAuthArray = ignoreAuth.toArray(stringArray);

        http.authorizeRequests()
                // ?????????????????? ??????????????????
                .antMatchers(ignoreAuthArray).anonymous()
                // ???????????????????????????????????????????????????
                .anyRequest().authenticated();


        //?????????????????????
        http.exceptionHandling()
                //???????????????????????????
                .authenticationEntryPoint(authenticationEntryPoint)
                //???????????????????????????
                .accessDeniedHandler(accessDeniedHandler);

        //???????????????
        http.addFilterBefore(integrationAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(jwtAuthenticationTokenFilter, IntegrationAuthenticationFilter.class);

        //????????????
        http.cors();
    }



    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * ?????????????????????
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
