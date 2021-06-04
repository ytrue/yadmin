package com.ytrue.yadmin.sys.service.impl;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.sys.dao.SysUserMapper;
import com.ytrue.yadmin.sys.dao.SysUserRoleMapper;
import com.ytrue.yadmin.sys.model.SysUser;
import com.ytrue.yadmin.sys.model.SysUserRole;
import com.ytrue.yadmin.sys.service.SysUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 系统用户
 *
 * @author lgh
 */
@Service("sysUserService")
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private SysUserRoleMapper sysUserRoleMapper;

    private SysUserMapper sysUserMapper;

    @Override
    public void saveUserAndUserRole(SysUser user) {
        user.setCreateTime(DateUtil.date());
        save(user);
        if (CollUtil.isEmpty(user.getRoleIdList())) {
            return;
        }
        //保存用户与角色关系
        sysUserRoleMapper.insertUserAndUserRole(user.getUserId(), user.getRoleIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserAndUserRole(SysUser user) {
        // 更新用户
        updateById(user);
        //先删除用户与角色关系
        sysUserRoleMapper.delete(new QueryWrapper<SysUserRole>().eq("user_id", user.getUserId()));

        if (CollUtil.isEmpty(user.getRoleIdList())) {
            return;
        }
        //保存用户与角色关系
        sysUserRoleMapper.insertUserAndUserRole(user.getUserId(), user.getRoleIdList());
    }

    @Override
    public void updatePasswordByUserId(Long userId, String newPassword) {
        SysUser user = new SysUser();
        user.setPassword(newPassword);
        user.setUserId(userId);
        sysUserMapper.updateById(user);
    }
}
