package com.ytrue.yadmin.modules.system.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


/**
 * @author ytrue
 * @date 2022/4/13 14:25
 * @description 系统用户
 */
@Data
@TableName("sys_user")
public class SysUser {
    /**
     * ID
     */
    @TableId
    @TableField("user_id")
    private Long userId;
    /**
     * 部门名称
     */
    @TableField("dept_id")
    private Long deptId;
    /**
     * 用户名
     */
    @TableField("username")
    private String username;
    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;
    /**
     * 性别
     */
    @TableField("gender")
    private String gender;
    /**
     * 手机号码
     */
    @TableField("phone")
    private String phone;
    /**
     * 邮箱
     */
    @TableField("email")
    private String email;
    /**
     * 头像地址
     */
    @TableField("avatar_name")
    private String avatarName;
    /**
     * 头像真实路径
     */
    @TableField("avatar_path")
    private String avatarPath;
    /**
     * 密码
     */
    @TableField("password")
    private String password;
    /**
     * 是否为admin账号
     */
    @TableField("is_admin")
    private boolean isAdmin;
    /**
     * 状态：1启用、0禁用
     */
    @TableField("enabled")
    private Long enabled;
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
     * 修改密码的时间
     */
    @TableField("pwd_reset_time")
    private Date pwdResetTime;
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
