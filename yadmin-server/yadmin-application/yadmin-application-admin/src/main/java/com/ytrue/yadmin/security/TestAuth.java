package com.ytrue.yadmin.security;

import com.ytrue.yadmin.security.user.LoginUser;
import com.ytrue.yadmin.security.integration.IntegrationAuthenticationEntity;
import com.ytrue.yadmin.security.integration.authenticator.AbstractPreparableIntegrationAuthenticator;
import org.springframework.stereotype.Component;

/**
 * @author ytrue
 * @date 2022/4/26 16:48
 * @description Test
 */
@Component
public class TestAuth extends AbstractPreparableIntegrationAuthenticator {
    @Override
    public Object authenticate(IntegrationAuthenticationEntity entity) {
        AdminUser user = new AdminUser();
        user.setId(1)
                .setUsername(entity.getAuthParameter("username"))
                .setPassword("$2a$10$7C5PuRa87rkpAMMi16peFuRQ72PI.FE/1Xd0yY1sJ6qul8fpMEH5y")
                .setEmail("ytrue@qq.com");
        LoginUser loginUser = new LoginUser();
        loginUser.setUser(user);
        return loginUser;
    }

    @Override
    public boolean support(IntegrationAuthenticationEntity entity) {
        String authType = entity.getAuthType();
        return "password".equals(authType);
    }
}
