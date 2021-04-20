package com.ytrue.yadmin.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ytrue.yadmin.modules.sys.model.SysUser;


/**
 * 系统用户
 * @author ytrue
 */
public interface SysUserService extends IService<SysUser> {

	/**
	 * 修改密码
	 * @param userId       用户ID
	 * @param newPassword  新密码
	 */
	void updatePasswordByUserId(Long userId, String newPassword);

	/**
	 * 保存用户与用户角色关系
	 * @param user
	 */
	void saveUserAndUserRole(SysUser user);


	/**
	 * 更新用户与用户角色关系
	 * @param user
	 */
	void updateUserAndUserRole(SysUser user);
}
