package com.ytrue.yadmin.modules.system.model;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 角色与菜单对应关系
 */
@Data
@TableName("sys_role_menu")
@ApiModel(value = "角色与菜单对应关系")
public class SysRoleMenu implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId
	@ApiModelProperty(value = "主键")
	private Long id;

	/**
	 * 角色ID
	 */
	@ApiModelProperty(value = "角色ID",required = true)
	private Long roleId;

	/**
	 * 菜单ID
	 */
	@ApiModelProperty(value = "菜单ID",required = true)
	private Long menuId;

}
