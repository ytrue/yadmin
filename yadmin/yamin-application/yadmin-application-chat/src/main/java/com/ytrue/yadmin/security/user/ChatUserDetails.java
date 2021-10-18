package com.ytrue.yadmin.security.user;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author ytrue
 * @date 2021/10/18 13:34
 * @description UserDetails
 */
@AllArgsConstructor
@ToString
public class ChatUserDetails implements UserDetails {

    private static final long serialVersionUID = -7302624463854565830L;


    /**
     * 用户名
     */
    private final String username;

    /**
     * 密码
     */
    private final String password;

    /**
     * id
     */
    private final Long userId;


    /**
     * 用户id
     *
     * @return
     */
    @Override
    public Long getUserId() {
        return this.userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
