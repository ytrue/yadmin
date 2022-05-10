package com.ytrue.yadmin.security.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author ytrue
 * @date 2022/4/26 11:27
 * @description User
 */
@Data
@Accessors(chain = true)
public class User implements Serializable {

    private Integer id;

    private String username;

    private String password;

    private String email;
}
