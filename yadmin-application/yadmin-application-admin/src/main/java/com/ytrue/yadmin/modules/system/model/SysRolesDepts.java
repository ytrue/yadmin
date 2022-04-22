package com.ytrue.yadmin.modules.system.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * @author ytrue
 * @date 2022/4/13 14:25
 * @description 角色部门关联
 */
@Data
@TableName("sys_roles_depts")
public class SysRolesDepts {
    /**
     *
     */
    @TableField("role_id")
    private Long roleId;
    /**
     *
     */
    @TableId
    @TableField("dept_id")
    private Long deptId;
}
