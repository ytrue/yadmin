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
* @description 菜单管理实体类
*/
@Data
@Builder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper=false)
@ApiModel(value = "菜单管理")
@TableName("sys_menu")
public class SysMenu implements Serializable {


    private static final long serialVersionUID = 2430789651255349191L;
    @TableId
    @TableField("menu_id")
    private Long menuId;


    @TableField("parent_id")
    @ApiModelProperty(value = "父菜单ID,一级菜单为0")
    private Long parentId;


    @TableField("router")
    @ApiModelProperty(value = "对应路由组件")
    private String router;


    @TableField("name")
    @ApiModelProperty(value = "菜单名称")
    private String name;


    @TableField("path")
    @ApiModelProperty(value = "path")
    private String path;


    @TableField("icon")
    @ApiModelProperty(value = "icon图标")
    private String icon;


    @TableField("perms")
    @ApiModelProperty(value = "授权(多个用逗号分隔，如：user:list,user:create)")
    private String perms;


    @TableField("redirect")
    @ApiModelProperty(value = "跳转地址")
    private String redirect;


    @TableField("order_num")
    @ApiModelProperty(value = "排序")
    private Object orderNum;


    @TableField("hidden")
    @ApiModelProperty(value = "是否显示:0=false,1=true")
    private Object hidden;


    @TableField("menu_type")
    @ApiModelProperty(value = "级别")
    private Object menuType;


    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;


    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

}
