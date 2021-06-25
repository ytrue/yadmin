package com.ytrue.yadmin.modules.system.service.impl;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.common.exeption.YadminException;
import com.ytrue.yadmin.dao.system.SysUserDao;
import com.ytrue.yadmin.dao.system.SysUserRoleDao;
import com.ytrue.yadmin.model.system.SysUser;
import com.ytrue.yadmin.model.system.SysUserRole;
import com.ytrue.yadmin.modules.system.service.SysMenuService;
import com.ytrue.yadmin.modules.system.service.SysUserService;
import com.ytrue.yadmin.modules.system.vo.UserInfoVO;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


/**
 * 系统用户
 *
 * @author lgh
 */
@Service("sysUserService")
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {

    private final SysUserRoleDao sysUserRoleDao;

    private final SysMenuService sysMenuService;


    @Override
    public void saveUserAndUserRole(SysUser user) {
        String username = user.getUsername();
        SysUser dbUser = getOne(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUsername, username));
        if (dbUser != null) {
            throw new YadminException("该用户已存在");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreateTime(LocalDateTime.now());

        save(user);
        if (!CollUtil.isEmpty(user.getRoleIdList())) {
            //保存用户与角色关系
            sysUserRoleDao.insertUserAndUserRole(user.getUserId(), user.getRoleIdList());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserAndUserRole(SysUser user) {
        String password = user.getPassword();
        SysUser dbUserNameInfo = getOne(new QueryWrapper<SysUser>().eq("username", user.getUsername()));
        if (dbUserNameInfo != null && !Objects.equals(dbUserNameInfo.getUserId(), user.getUserId())) {
            throw new YadminException("该用户已存在");
        }
        if (StrUtil.isBlank(password)) {
            user.setPassword(null);
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        // 更新用户
        updateById(user);
        //先删除用户与角色关系
        sysUserRoleDao.delete(new QueryWrapper<SysUserRole>().eq("user_id", user.getUserId()));
        if (!CollUtil.isEmpty(user.getRoleIdList())) {
            //保存用户与角色关系
            sysUserRoleDao.insertUserAndUserRole(user.getUserId(), user.getRoleIdList());
        }

    }

    @Override
    public UserInfoVO getUserInfo(Claims claims) {
        //去获得用户名和头像
        String userName = (String) claims.get("user_name");
        SysUser username = getOne(new QueryWrapper<SysUser>().eq("username", userName));
        //获得了该角色的权限
        List<?> authorities = (List<?>) claims.get("authorities");
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setNickname(username.getNickname());
        userInfoVO.setAvatar(username.getAvatar());
        userInfoVO.setPermissions(authorities);
        userInfoVO.setMenu(sysMenuService.myMenuTree(username.getUserId(), 2));
        return userInfoVO;
    }
}
