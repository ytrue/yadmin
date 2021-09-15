package com.ytrue.yadmin.security.user;

/**
 * @author ytrue
 * @date 2021/7/3 22:30
 * @description UserDetails
 */
public interface UserDetails extends org.springframework.security.core.userdetails.UserDetails {

    /**
     * 用户id
     *
     * @return
     */
    Long getUserId();

}
