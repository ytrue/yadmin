package com.ytrue.yadmin.sys.aspect;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description SysUserDetails
 */
@AllArgsConstructor
@ToString
public class SysUserDetails implements UserDetails {

    /**
     * 用户名
     */
    private final String username;

    /**
     * 密码
     */
    private final String password;

    /**
     * 权限集合
     */
    private final List<GrantedAuthority> authorities;

    /**
     * 图片
     */
    private final String images;

    /**
     * id
     */
    private final Long userId;


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

}
