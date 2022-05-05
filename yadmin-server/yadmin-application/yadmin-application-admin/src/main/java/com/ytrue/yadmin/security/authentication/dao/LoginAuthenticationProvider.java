package com.ytrue.yadmin.security.authentication.dao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author ytrue
 * @date 2022/4/26 14:03
 * @description 自定义LoginAuthenticationProvider继承DaoAuthenticationProvider重写
 */
@Component
public class LoginAuthenticationProvider extends DaoAuthenticationProvider {


    public LoginAuthenticationProvider(
            @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder
    ) {
        super();
        // 这个地方一定要对userDetailsService，passwordEncoder赋值，不然是null
        setUserDetailsService(userDetailsService);
        setPasswordEncoder(passwordEncoder);
    }


    @Override
    protected void additionalAuthenticationChecks(
            UserDetails userDetails,
            UsernamePasswordAuthenticationToken authentication
    ) throws AuthenticationException {
        if (authentication.getCredentials() != null && !"".equals(authentication.getCredentials())) {
            if (!this.getPasswordEncoder().matches(authentication.getCredentials().toString(), userDetails.getPassword())) {
                throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
            }
        }
    }
}
