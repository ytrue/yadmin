package com.ytrue.yadmin.modules.system.model.vo;

import cn.hutool.core.lang.tree.Tree;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author ytrue
 * @date 2021/6/25 14:36
 * @description UserInfoVO
 */
@Data
public class UserInfoVO {
    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称")
    private String nickname;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String avatar;

    /**
     * 用户的权限
     */
    @ApiModelProperty(value = "用户的权限")
    private List<?> permissions;


    /**
     * 菜单
     */
    @ApiModelProperty(value = "菜单")
    List<Tree<String>> menu;
}
