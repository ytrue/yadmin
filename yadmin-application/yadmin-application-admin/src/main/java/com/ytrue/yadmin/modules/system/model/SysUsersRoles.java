package com.ytrue.yadmin.modules.system.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * @author ytrue
 * @date 2022/4/13 14:25
 * @description 用户角色关联
 */
@Data
@TableName("sys_users_roles")
public class SysUsersRoles {
    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 角色ID
     */
    @TableId
    @TableField("role_id")
    private Long roleId;
}
