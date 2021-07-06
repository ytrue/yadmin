package com.ytrue.yadmin.security;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ytrue.yadmin.exeption.YadminException;
import com.ytrue.yadmin.modules.system.dao.SysMenuDao;
import com.ytrue.yadmin.modules.system.dao.SysUserDao;
import com.ytrue.yadmin.modules.system.model.SysMenu;
import com.ytrue.yadmin.modules.system.model.SysUser;
import com.ytrue.yadmin.security.integration.IntegrationAuthenticationContext;
import com.ytrue.yadmin.security.integration.IntegrationAuthenticationEntity;
import com.ytrue.yadmin.security.integration.authenticator.IntegrationAuthenticator;
import com.ytrue.yadmin.security.user.SysUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 集成认证-用户细节服务
 */
@Service
@Slf4j
public class IntegrationUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SysMenuDao sysMenuDao;

    @Autowired
    private SysUserDao SysUserDao;

    private List<IntegrationAuthenticator> authenticators;

    /**
     * 系统管理员id
     */
    private final static int SUPER_ADMIN_ID = 1;


    @Autowired(required = false)
    public void setIntegrationAuthenticators(List<IntegrationAuthenticator> authenticators) {
        this.authenticators = authenticators;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.info("我是IntegrationUserDetailsService：我被执行到了");
        //去获得上下文
        IntegrationAuthenticationEntity entity = IntegrationAuthenticationContext.get();
        //判断是否为空
        if (entity == null) {
            entity = new IntegrationAuthenticationEntity();
        }
        //判断是否支持集成认证类型
        SysUser user = (SysUser) authenticate(entity);
        //传递
        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(getUserPermissions(user.getUserId()).toArray(new String[0]));
        return new SysUserDetails(user.getUsername(), user.getPassword(), authorityList, "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif", user.getUserId());
    }


    /**
     * 获得权限
     *
     * @param userId
     * @return
     */
    private Set<String> getUserPermissions(Long userId) {
        List<String> permsList;
        //系统管理员，拥有最高权限
        if (userId == SUPER_ADMIN_ID) {
            List<SysMenu> menuList = sysMenuDao.selectList(Wrappers.emptyWrapper());
            permsList = menuList.stream().map(SysMenu::getPerms).collect(Collectors.toList());
        } else {
            permsList = SysUserDao.queryAllPerms(userId);
        }
        return permsList.stream().flatMap((perms) -> {
                    if (StrUtil.isBlank(perms)) {
                        return null;
                    }
                    return Arrays.stream(perms.trim().split(","));
                }
        ).collect(Collectors.toSet());
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
        throw new YadminException("无效的auth_type参数值！");
    }
}
