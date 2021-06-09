package com.ytrue.yadmin.sys.model;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 系统配置信息
 */
@Data
@TableName("sys_menu")
public class SysMenu implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @TableId
    private Long menuId;

    /**
     * 父菜单ID，一级菜单为0
     */
    private Long parentId;

    /**
     * 路由名称
     */
    @NotBlank(message = "路由名称不能为空")
    private String router;

    /**
     * 菜单名称
     */
    @NotBlank(message = "菜单名称不能为空")
    private String name;


    /**
     * path
     */
    private String path;

    /**
     * 跳转地址
     */
    private String redirect;


    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    private String perms;


    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 是否显示
     */
    private Boolean hidden;

    /**
     * 级别，一共四级
     */
    private Integer level;

    /**
     * icon
     */
    private String icon;

}
