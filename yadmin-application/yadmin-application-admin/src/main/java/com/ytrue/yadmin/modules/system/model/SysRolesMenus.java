package com.ytrue.yadmin.modules.system.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author ytrue
 * @date 2022/4/13 14:25
 * @description 角色菜单关联
 */
@Data
@TableName("sys_roles_menus")
public class SysRolesMenus {
    /**
     * 菜单ID
     */
    @TableField("menu_id")
    private Long menuId;
    /**
     * 角色ID
     */
    @TableId
    @TableField("role_id")
    private Long roleId;
}
