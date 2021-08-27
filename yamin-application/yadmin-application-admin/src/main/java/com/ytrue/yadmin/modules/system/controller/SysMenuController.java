package com.ytrue.yadmin.modules.system.controller;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ytrue.yadmin.enums.StrPool;
import com.ytrue.yadmin.log.annotation.SysLog;
import com.ytrue.yadmin.exeption.YadminException;

import com.ytrue.yadmin.modules.system.model.SysMenu;
import com.ytrue.yadmin.modules.system.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 系统菜单
 */
@Slf4j
@Api(tags = "菜单")
@RestController
@RequestMapping("sys/menu")
@AllArgsConstructor
public class SysMenuController {

    private final SysMenuService sysMenuService;


    @GetMapping("table")
    @ApiOperation("获取菜单页面的表")
    public List<SysMenu> table() {
        return sysMenuService.list(new QueryWrapper<SysMenu>().lambda().orderByAsc(SysMenu::getOrderNum));
    }


    /**
     * 获取用户所拥有的菜单(不包括按钮)
     * 所有菜单列表(用于新建、修改角色时 获取菜单的信息)
     *
     * @return {@link List<SysMenu>}
     */
    @GetMapping("list")
    @ApiOperation("获取用户所拥有的菜单(不包括按钮)")
    public List<SysMenu> list() {
        return sysMenuService.list(
                new QueryWrapper<SysMenu>().ne("menu_type", 3).lambda().orderByAsc(SysMenu::getOrderNum));
    }

    @GetMapping("{menuId}/info")
    @ApiOperation("菜单信息")
    @PreAuthorize("@pms.hasPermission('sys:menu:info')")
    public SysMenu info(@PathVariable("menuId") Long menuId) {
        SysMenu sysMenu = sysMenuService.getById(menuId);
        Assert.notNull(sysMenu, StrPool.DATA_DOES_NOT_EXIST.getMessage());
        return sysMenu;
    }

    @SysLog
    @PostMapping
    @ApiOperation("保存菜单")
    @PreAuthorize("@pms.hasPermission('sys:menu:save')")
    public void save(@Validated @RequestBody SysMenu menu) {
        verifyForm(menu);
        sysMenuService.save(menu);
    }


    @SysLog
    @PutMapping
    @ApiOperation("修改菜单")
    @PreAuthorize("@pms.hasPermission('sys:menu:update')")
    public void update(@Validated @RequestBody SysMenu menu) {
        verifyForm(menu);
        sysMenuService.updateById(menu);
    }

    @SysLog
    @DeleteMapping
    @ApiOperation("删除菜单")
    @PreAuthorize("@pms.hasPermission('sys:menu:delete')")
    public void delete(@RequestBody List<Long> menuIds) {
        menuIds.forEach(sysMenuService::deleteMenuAndRoleMenu);
    }


    /**
     * 验证参数是否正确
     *
     * @param menu
     * @throws {@link YadminException}
     */
    private void verifyForm(SysMenu menu) {
        if (menu.getParentId().equals(menu.getMenuId()) && menu.getMenuId() != 0) {
            throw new YadminException(StrPool.YOU_CANT_BE_YOUR_SUPERIOR.getMessage());
        }
        if (menu.getParentId() == 0 && menu.getMenuType() != 0) {
            throw new YadminException(StrPool.MENU_TYPE_CAN_ONLY_BE_HEADER_MENU.getMessage());
        }
        SysMenu parentMenu = sysMenuService.getById(menu.getParentId());
        if (null != parentMenu) {
            Integer superMenuType = menu.getMenuType() - 1;
            if (!parentMenu.getMenuType().equals(superMenuType)) {
                throw new YadminException(StrPool.PLEASE_SELECT_THE_CORRESPONDING_MENU_TYPE.getMessage());
            }
        }
    }
}
