package com.ytrue.yadmin.security.authenticator;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ytrue.yadmin.modules.system.dao.SysUserDao;
import com.ytrue.yadmin.modules.system.model.SysUser;
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
 * @date 2021/4/8 15:36
 * @description 普通认证器（用户名+密码）
 * <p>
 * service有多个实现类的时候，注入的时候需要按照@Qualifier() 注入，
 * 加了@Primary 可以实现一个默认的注入类
 */
@Component
@Primary
@AllArgsConstructor
public class UsernamePasswordAuthenticator extends AbstractPreparableIntegrationAuthenticator {


    private final SysUserDao SysUserDao;

    /**
     * 预处理
     *
     * @param entity 集成认证实体,UsernameNotFoundException这个异常不能抛
     * @return
     */
    @Override
    public SysUser authenticate(IntegrationAuthenticationEntity entity) {
        String username = entity.getAuthParameter("username");
        String password = entity.getAuthParameter("password");

        if (StrUtil.hasBlank(username) || StrUtil.hasBlank(password)) {
            throw new InternalAuthenticationServiceException("用户名或密码不能为空");
        }
        SysUser user = SysUserDao.selectOne(new QueryWrapper<SysUser>().eq("username", username));
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
     * 结束
     *
     * @param entity 集成认证实体
     * @return
     */
    @Override
    public boolean support(IntegrationAuthenticationEntity entity) {
        return StringUtils.isEmpty(entity.getAuthType());
    }
}
