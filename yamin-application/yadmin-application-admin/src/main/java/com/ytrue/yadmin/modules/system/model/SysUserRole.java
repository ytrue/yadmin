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
 * @description 用户与角色对应关系
 */
@Data
@TableName("sys_user_role")
@ApiModel(value = "用户与角色对应关系")
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    @ApiModelProperty(value = "主键",required = true)
    private Long id;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID",required = true)
    private Long userId;

    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID",required = true)
    private Long roleId;
}
