package com.ytrue.yadmin.modules.system.model;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import java.time.LocalDateTime;

/**
* @author ytrue
* @date 2022-06-29
* @description 系统用户实体类
*/
@Data
@Builder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper=false)
@ApiModel(value = "系统用户")
@TableName("sys_user")
public class SysUser implements Serializable {


    private static final long serialVersionUID = -2488831715409513490L;
    @TableId
    @TableField("user_id")
    @ApiModelProperty(value = "用户id")
    private Long userId;


    @TableField("username")
    @ApiModelProperty(value = "用户名")
    private String username;


    @TableField("nickname")
    @ApiModelProperty(value = "昵称")
    private String nickname;


    @TableField("avatar")
    @ApiModelProperty(value = "头像")
    private String avatar;


    @TableField("password")
    @ApiModelProperty(value = "密码")
    private String password;


    @TableField("email")
    @ApiModelProperty(value = "邮箱")
    private String email;


    @TableField("mobile")
    @ApiModelProperty(value = "手机号")
    private String mobile;


    @TableField("status")
    @ApiModelProperty(value = "状态 :0=禁用,1=正常")
    private Object status;


    @TableField("create_user_id")
    @ApiModelProperty(value = "创建者ID")
    private Long createUserId;


    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;


    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

}
