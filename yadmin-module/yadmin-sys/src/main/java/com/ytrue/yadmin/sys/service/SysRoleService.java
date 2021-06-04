package com.ytrue.yadmin.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ytrue.yadmin.sys.model.SysRole;


import java.util.List;

/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description SysRoleService
 */
public interface SysRoleService extends IService<SysRole> {

	/**
	 * 根据id批量删除
	 * @param roleIds
	 */
	void deleteBatch(Long[] roleIds);

	/**
	 * 保存角色 与 角色菜单关系
	 * @param role
	 */
	void saveRoleAndRoleMenu(SysRole role);

	/**
	 * 更新角色 与 角色菜单关系
	 * @param role
	 */
	void updateRoleAndRoleMenu(SysRole role);

	/**
	 * 根据用户ID，获取角色ID列表
	 * @param userId 用户id
	 * @return 角色id列表
	 */
	List<Long> listRoleIdByUserId(Long userId);

}
