package com.ytrue.yadmin.security;

import cn.hutool.core.convert.Convert;
import com.ytrue.yadmin.model.chat.ChatContact;
import com.ytrue.yadmin.security.integration.IntegrationAuthenticationContext;
import com.ytrue.yadmin.security.integration.IntegrationAuthenticationEntity;
import com.ytrue.yadmin.security.integration.authenticator.IntegrationAuthenticator;
import com.ytrue.yadmin.security.user.ChatUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ytrue
 * @date 2021/10/18 13:34
 * @description 集成认证-用户细节服务
 */
@Slf4j
@Service
public class IntegrationUserDetailsServiceImpl implements UserDetailsService {

    private List<IntegrationAuthenticator> authenticators;


    @Autowired(required = false)
    public void setIntegrationAuthenticators(List<IntegrationAuthenticator> authenticators) {
        this.authenticators = authenticators;
    }


    @Override
    public ChatUserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //去获得上下文
        IntegrationAuthenticationEntity entity = IntegrationAuthenticationContext.get();
        //判断是否为空
        if (entity == null) {
            entity = new IntegrationAuthenticationEntity();
        }
        //判断是否支持集成认证类型
        ChatContact user = (ChatContact) authenticate(entity);
        return new ChatUserDetails(user.getUsername(), user.getPassword(), Convert.toLong(user.getContactId()));
    }


    /**
     * 判断是否支持集成认证类型
     *
     * @param entity
     * @return
     */
    private Object authenticate(IntegrationAuthenticationEntity entity) {
        if (this.authenticators != null) {
            for (IntegrationAuthenticator authenticator : authenticators) {
                if (authenticator.support(entity)) {
                    return authenticator.authenticate(entity);
                }
            }
        }
        throw new InternalAuthenticationServiceException("无效的auth_type参数值！");
    }
}
