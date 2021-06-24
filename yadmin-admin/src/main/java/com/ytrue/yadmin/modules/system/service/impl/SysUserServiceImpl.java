package com.ytrue.yadmin.modules.system.service.impl;


import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.dao.system.SysUserDao;
import com.ytrue.yadmin.dao.system.SysUserRoleDao;
import com.ytrue.yadmin.model.system.SysUser;
import com.ytrue.yadmin.model.system.SysUserRole;
import com.ytrue.yadmin.modules.system.service.SysUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


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

    private final SysUserDao SysUserDao;

    @Override
    public void saveUserAndUserRole(SysUser user) {
        user.setCreateTime(LocalDateTime.now());
        save(user);
        if (CollUtil.isEmpty(user.getRoleIdList())) {
            return;
        }
        //保存用户与角色关系
        sysUserRoleDao.insertUserAndUserRole(user.getUserId(), user.getRoleIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserAndUserRole(SysUser user) {
        // 更新用户
        updateById(user);
        //先删除用户与角色关系
        sysUserRoleDao.delete(new QueryWrapper<SysUserRole>().eq("user_id", user.getUserId()));

        if (CollUtil.isEmpty(user.getRoleIdList())) {
            return;
        }
        //保存用户与角色关系
        sysUserRoleDao.insertUserAndUserRole(user.getUserId(), user.getRoleIdList());
    }

    @Override
    public void updatePasswordByUserId(Long userId, String newPassword) {
        SysUser user = new SysUser();
        user.setPassword(newPassword);
        user.setUserId(userId);
        SysUserDao.updateById(user);
    }
}
