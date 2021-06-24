package com.ytrue.yadmin.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ytrue.yadmin.model.system.SysUser;


/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description SysRoleService 系统用户
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 修改密码
     *
     * @param userId      用户ID
     * @param newPassword 新密码
     */
    void updatePasswordByUserId(Long userId, String newPassword);

    /**
     * 保存用户与用户角色关系
     *
     * @param user
     */
    void saveUserAndUserRole(SysUser user);


    /**
     * 更新用户与用户角色关系
     *
     * @param user
     */
    void updateUserAndUserRole(SysUser user);
}
