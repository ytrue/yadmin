package com.ytrue.yadmin.model.system;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 角色与菜单对应关系
 */
@Data
@TableName("sys_role_menu")
public class SysRoleMenu implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId
	private Long id;

	/**
	 * 角色ID
	 */
	private Long roleId;

	/**
	 * 菜单ID
	 */
	private Long menuId;

}