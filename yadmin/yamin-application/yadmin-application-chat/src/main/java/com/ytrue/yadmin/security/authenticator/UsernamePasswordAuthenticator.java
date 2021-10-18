package com.ytrue.yadmin.security.authenticator;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ytrue.yadmin.dao.chat.ChatContactDAO;
import com.ytrue.yadmin.model.chat.ChatContact;
import com.ytrue.yadmin.security.integration.IntegrationAuthenticationEntity;
import com.ytrue.yadmin.security.integration.authenticator.AbstractPreparableIntegrationAuthenticator;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author ytrue
 * @date 2021/10/18 13:34
 * @description 普通认证器（用户名+密码）
 * <p>
 * service有多个实现类的时候，注入的时候需要按照@Qualifier() 注入，
 * 加了@Primary 可以实现一个默认的注入类
 */
@Component
@Primary
@AllArgsConstructor
public class UsernamePasswordAuthenticator extends AbstractPreparableIntegrationAuthenticator {

    private final ChatContactDAO chatContactDAO;

    /**
     * 处理集成认证
     *
     * @param entity 集成认证实体
     * @return 用户表实体
     */
    @Override
    public Object authenticate(IntegrationAuthenticationEntity entity) {
        String username = entity.getAuthParameter("username");
        String password = entity.getAuthParameter("password");

        if (StrUtil.hasBlank(username) || StrUtil.hasBlank(password)) {
            throw new InternalAuthenticationServiceException("用户名或密码不能为空");
        }
        ChatContact user = chatContactDAO.selectOne(new QueryWrapper<ChatContact>().eq("username", username));
        if (null == user) {
            throw new InternalAuthenticationServiceException("账号不存在");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InternalAuthenticationServiceException("密码错误");
        }
        return user;
    }

    /**
     * 判断是否支持集成认证类型
     *
     * @param entity 集成认证实体
     * @return
     */
    @Override
    public boolean support(IntegrationAuthenticationEntity entity) {
        return StringUtils.isEmpty(entity.getAuthType());
    }
}
