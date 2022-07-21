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
* @description 角色与菜单对应关系实体类
*/
@Data
@Builder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper=false)
@ApiModel(value = "角色与菜单对应关系")
@TableName("sys_role_menu")
public class SysRoleMenu implements Serializable {


    private static final long serialVersionUID = -4661231521690339037L;
    @TableId
    private Long id;


    @TableField("role_id")
    @ApiModelProperty(value = "角色ID")
    private Long roleId;


    @TableField("menu_id")
    @ApiModelProperty(value = "菜单ID")
    private Long menuId;

}
