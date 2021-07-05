package com.ytrue.yadmin.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ytrue.yadmin.common.annotation.AutoValid;
import com.ytrue.yadmin.common.annotation.SysLog;
import com.ytrue.yadmin.common.annotation.WrapResp;
import com.ytrue.yadmin.common.exeption.YadminException;
import com.ytrue.yadmin.model.system.SysMenu;
import com.ytrue.yadmin.modules.system.service.SysMenuService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Consumer;


/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 系统菜单
 */
@Slf4j
@WrapResp
@RestController
@RequestMapping("sys/menu")
@AllArgsConstructor
public class SysMenuController {

    private final SysMenuService sysMenuService;

    /**
     * 获取菜单页面的表
     *
     * @return {@link List<SysMenu>}
     */
    @GetMapping("table")
    public List<SysMenu> table() {
        return sysMenuService.list(new QueryWrapper<SysMenu>().orderByAsc("order_num"));
    }


    /**
     * 获取用户所拥有的菜单(不包括按钮)
     * 所有菜单列表(用于新建、修改角色时 获取菜单的信息)
     *
     * @return {@link List<SysMenu>}
     */
    @GetMapping("list")
    public List<SysMenu> list() {
        return sysMenuService.list(
                new QueryWrapper<SysMenu>().ne("menu_type", 3).orderByAsc("order_num"));
    }

    /**
     * 菜单信息
     */
    @GetMapping("{menuId}")
    @PreAuthorize("@pms.hasPermission('sys:menu:info')")
    public SysMenu info(@PathVariable("menuId") Long menuId) {
        return sysMenuService.getById(menuId);
    }

    /**
     * 保存
     *
     * @param menu
     */
    @SysLog("保存菜单")
    @PostMapping
    @AutoValid(entity = SysMenu.class)
    @PreAuthorize("@pms.hasPermission('sys:menu:save')")
    public void save(@RequestBody SysMenu menu) {
        verifyForm(menu);
        sysMenuService.save(menu);
    }


    /**
     * 修改
     *
     * @param menu
     */
    @SysLog("修改菜单")
    @PutMapping
    @AutoValid(entity = SysMenu.class)
    @PreAuthorize("@pms.hasPermission('sys:menu:update')")
    public void update(@RequestBody SysMenu menu) {
        verifyForm(menu);
        sysMenuService.updateById(menu);
    }

    /**
     * 删除
     *
     * @param menuIds
     */
    @SysLog("删除菜单")
    @DeleteMapping
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
            throw new YadminException("自己不能是自己的上级");
        }
        if (menu.getParentId() == 0 && menu.getMenuType() != 0) {
            throw new YadminException("菜单类型只能是头部菜单");
        }
        SysMenu parentMenu = sysMenuService.getById(menu.getParentId());
        if (null != parentMenu) {
            Integer superMenuType = menu.getMenuType() - 1;
            if (!parentMenu.getMenuType().equals(superMenuType)) {
                throw new YadminException("请选择对应的菜单类型");
            }
        }
    }
}
