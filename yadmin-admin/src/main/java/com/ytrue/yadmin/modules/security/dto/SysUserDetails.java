package com.ytrue.yadmin.modules.security.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description SysUserDetails
 */
public class SysUserDetails implements UserDetails {

    private final String username;
    private final String password;
    private final List<GrantedAuthority> authorities;
    private final String images;
    private final Long userId;

    public SysUserDetails(String username, String password, String images, List<GrantedAuthority> authorities, Long userId) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.images = images;
        this.userId = userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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

    public String getImages() {
        return this.images;
    }

    public Long getUserId() {
        return this.userId;
    }

    @Override
    public String toString() {
        return "SysUserDetails{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                ", images='" + images + '\'' +
                '}';
    }
}
