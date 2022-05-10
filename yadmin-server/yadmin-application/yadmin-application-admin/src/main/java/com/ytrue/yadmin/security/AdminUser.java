package com.ytrue.yadmin.security;

import com.ytrue.yadmin.security.user.User;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author ytrue
 * @date 2022/5/10 16:02
 * @description AdminUser
 */
@Data
@Accessors(chain = true)
public class AdminUser implements User {


    private Integer id;

    private String username;

    private String password;

    private String email;

    @Override
    public String userId() {
        return getId().toString();
    }

    @Override
    public List<String> authorities() {
        return null;
    }

    @Override
    public String password() {
        return getPassword();
    }

    @Override
    public String username() {
        return getUsername();
    }
}
