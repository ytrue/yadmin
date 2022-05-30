package com.ytrue.yadmin.modules.system.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author ytrue
 * @date 2022/4/13 14:25
 * @description 系统菜单
 */
@Data
@TableName("sys_menu")
@Builder
@ApiModel(value = "系统菜单")
@Accessors(chain = true)
public class SysMenu {
    /**
     * ID
     */
    @TableId
    @TableField("menu_id")
    private Long menuId;
    /**
     * 上级菜单ID
     */
    @TableField("pid")
    private Long pid;
    /**
     * 子菜单数目
     */
    @TableField("sub_count")
    private Integer subCount;
    /**
     * 菜单类型
     */
    @TableField("type")
    private Integer type;
    /**
     * 菜单标题
     */
    @TableField("title")
    private String title;
    /**
     * 组件名称
     */
    @TableField("name")
    private String name;
    /**
     * 组件
     */
    @TableField("component")
    private String component;
    /**
     * 排序
     */
    @TableField("menu_sort")
    private Integer menuSort;
    /**
     * 图标
     */
    @TableField("icon")
    private String icon;
    /**
     * 链接地址
     */
    @TableField("path")
    private String path;
    /**
     * 是否外链
     */
    @TableField("i_frame")
    private boolean iFrame;
    /**
     * 缓存
     */
    @TableField("cache")
    private boolean cache;
    /**
     * 隐藏
     */
    @TableField("hidden")
    private boolean hidden;
    /**
     * 权限
     */
    @TableField("permission")
    private String permission;
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
