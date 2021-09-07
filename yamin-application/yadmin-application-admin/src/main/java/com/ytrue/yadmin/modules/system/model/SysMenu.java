package com.ytrue.yadmin.modules.system.model;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 菜单
 */
@Data
@TableName("sys_menu")
@ApiModel(value = "菜单")
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1616676968007770238L;
    /**
     * 菜单ID
     */
    @TableId
    @ApiModelProperty(value = "主键")
    private Long menuId;

    /**
     * 父菜单ID，一级菜单为0
     */
    @ApiModelProperty(value = "父菜单ID，一级菜单为0")
    private Long parentId;

    /**
     * 路由名称
     */
    @ApiModelProperty(value = "路由名称")
    private String router;

    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称",required = true)
    @NotBlank(message = "菜单名称不能为空")
    private String name;


    /**
     * path
     */
    @ApiModelProperty(value = "路径")
    private String path;

    /**
     * 跳转地址
     */
    @ApiModelProperty(value = "跳转地址")
    private String redirect;


    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    @ApiModelProperty(value = "授权(多个用逗号分隔，如：user:list,user:create)")
    private String perms;


    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    @TableField("order_num")
    private Integer orderNum;

    /**
     * 是否显示
     */
    @ApiModelProperty(value = "是否显示")
    private Boolean hidden;

    /**
     * 级别，一共四级
     */
    @ApiModelProperty(value = "菜单类型 一共四级")
    @TableField("menu_type")
    private Integer menuType;

    /**
     * icon
     */
    @ApiModelProperty(value = "图标")
    private String icon;

}
