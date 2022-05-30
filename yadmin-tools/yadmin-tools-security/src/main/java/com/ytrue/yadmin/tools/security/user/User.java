package com.ytrue.yadmin.tools.security.user;

import java.util.List;

/**
 * @author ytrue
 * @date 2022/5/10 15:53
 * @description User  ,为什么不用get开头的方法，以为json序列化会把get方法也加入
 */
public interface User {

    /**
     * 用户id
     *
     * @return
     */
    String userId();


    /**
     * 获取权限
     *
     * @return
     */
    List<String> authorities();


    /**
     * 密码
     *
     * @return
     */
    String password();

    /**
     * 获取用户名
     *
     * @return
     */
    String username();


}
