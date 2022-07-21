package com.ytrue.yadmin.modules.system.model;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;

/**
* @author ytrue
* @date 2022-06-29
* @description 用户与角色对应关系实体类
*/
@Data
@Builder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper=false)
@ApiModel(value = "用户与角色对应关系")
@TableName("sys_user_role")
public class SysUserRole implements Serializable {


    private static final long serialVersionUID = 7373142138958749506L;
    @TableId
    private Long id;


    @TableField("user_id")
    @ApiModelProperty(value = "用户ID")
    private Long userId;


    @TableField("role_id")
    @ApiModelProperty(value = "角色ID")
    private Long roleId;

}
