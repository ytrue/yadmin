package com.ytrue.yadmin.model;

import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.*;
import java.time.LocalDateTime;

/**
* @author ytrue
* @date 2022-06-20
* @description 系统用户实体类
*/
@Data
@Builder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper=false)
@ApiModel(value = "系统用户")
@TableName("sys_user")
public class SysUser {

    @TableId
    @TableField("user_id")
    @ApiModelProperty(value = "ID")
    private Long userId;


    @TableField("dept_id")
    @ApiModelProperty(value = "部门名称")
    private Long deptId;


    @TableField("username")
    @ApiModelProperty(value = "用户名")
    private String username;


    @TableField("nick_name")
    @ApiModelProperty(value = "昵称")
    private String nickName;


    @TableField("gender")
    @ApiModelProperty(value = "性别")
    private String gender;


    @TableField("phone")
    @ApiModelProperty(value = "手机号码")
    private String phone;


    @TableField("email")
    @ApiModelProperty(value = "邮箱")
    private String email;


    @TableField("avatar_name")
    @ApiModelProperty(value = "头像地址")
    private String avatarName;


    @TableField("avatar_path")
    @ApiModelProperty(value = "头像真实路径")
    private String avatarPath;


    @TableField("password")
    @ApiModelProperty(value = "密码")
    private String password;


    @TableField("is_admin")
    @ApiModelProperty(value = "是否为admin账号")
    private Object isAdmin;


    @TableField("enabled")
    @ApiModelProperty(value = "状态：1启用、0禁用")
    private Long enabled;


    @TableField("create_by")
    @ApiModelProperty(value = "创建者")
    private String createBy;


    @TableField("update_by")
    @ApiModelProperty(value = "更新者")
    private String updateBy;


    @TableField("pwd_reset_time")
    @ApiModelProperty(value = "修改密码的时间")
    private LocalDateTime pwdResetTime;


    @TableField("create_time")
    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;


    @TableField("update_time")
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

}
