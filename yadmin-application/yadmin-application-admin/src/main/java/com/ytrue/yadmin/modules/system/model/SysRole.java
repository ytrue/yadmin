package com.ytrue.yadmin.modules.system.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


/**
 * @author ytrue
 * @date 2022/4/13 14:25
 * @description 角色表
 */
@Data
@TableName("sys_role")
public class SysRole {
    /**
     * ID
     */
    @TableId
    @TableField("role_id")
    private Long roleId;
    /**
     * 名称
     */
    @TableField("name")
    private String name;
    /**
     * 角色级别
     */
    @TableField("level")
    private Integer level;
    /**
     * 描述
     */
    @TableField("description")
    private String description;
    /**
     * 数据权限
     */
    @TableField("data_scope")
    private String dataScope;
    /**
     * 创建者
     */
    @TableField("create_by")
    private String createBy;
    /**
     * 更新者
     */
    @TableField("update_by")
    private String updateBy;
    /**
     * 创建日期
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
}
