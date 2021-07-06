package com.ytrue.yadmin.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ytrue.yadmin.modules.system.model.SysUser;
import com.ytrue.yadmin.modules.system.vo.UserInfoVO;
import io.jsonwebtoken.Claims;


/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description SysRoleService 系统用户
 */
public interface SysUserService extends IService<SysUser> {

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


    /**
     * 获得我的基本信息，菜单，权限
     *
     * @param claims
     * @return
     */
    UserInfoVO getUserInfo(Claims claims);
}
