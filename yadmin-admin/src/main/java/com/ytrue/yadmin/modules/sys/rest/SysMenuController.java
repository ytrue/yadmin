package com.ytrue.yadmin.modules.sys.rest;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ytrue.yadmin.common.annotation.WrapResp;
import com.ytrue.yadmin.common.exeption.YadminException;
import com.ytrue.yadmin.common.response.ResponseData;
import com.ytrue.yadmin.modules.sys.annotation.SysLog;
import com.ytrue.yadmin.modules.sys.constant.Constant;
import com.ytrue.yadmin.modules.sys.constant.MenuType;
import com.ytrue.yadmin.modules.sys.model.SysMenu;
import com.ytrue.yadmin.modules.sys.service.SysMenuService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 系统菜单
 */
@WrapResp
@RestController
@RequestMapping("/sys/menu")
@AllArgsConstructor
public class SysMenuController {

    private final SysMenuService sysMenuService;

    /**
     * 获取菜单页面的表
     *
     * @return
     */
    @GetMapping("/table")
    public List<SysMenu> table() {
        return sysMenuService.list(new QueryWrapper<SysMenu>().orderByAsc("order_num"));
    }


    /**
     * 获取用户所拥有的菜单(不包括按钮)
     * 所有菜单列表(用于新建、修改角色时 获取菜单的信息)
     *
     * @return
     */
    @GetMapping("/list")
    public List<SysMenu> list() {
        return sysMenuService.list(
                new QueryWrapper<SysMenu>().orderByAsc("order_num"));
    }

    /**
     * 菜单信息
     */
    @GetMapping("/info/{menuId}")
    @PreAuthorize("@pms.hasPermission('sys:menu:info')")
    public SysMenu info(@PathVariable("menuId") Long menuId) {
        return sysMenuService.getById(menuId);
    }

    /**
     * 保存
     *
     * @param menu
     * @param b
     */
    @SysLog("保存菜单")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('sys:menu:save')")
    public void save(@Valid @RequestBody SysMenu menu, BindingResult b) {
        //数据校验
        verifyForm(menu);
        sysMenuService.save(menu);
    }


    /**
     * 修改
     *
     * @param menu
     * @param b
     * @return
     */
    @SysLog("修改菜单")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('sys:menu:update')")
    public ResponseData<String> update(@Valid @RequestBody SysMenu menu, BindingResult b) {
        //数据校验
        verifyForm(menu);
//        if (menu.getType() == MenuType.MENU.getValue()) {
//            if (StrUtil.isBlank(menu.getUrl())) {
//                return ResponseData.fail("菜单URL不能为空");
//            }
//        }
        sysMenuService.updateById(menu);
        return ResponseData.success();
    }

    /**
     * 删除
     *
     * @param menuId
     */
    @SysLog("删除菜单")
    @DeleteMapping("/{menuId}")
    @PreAuthorize("@pms.hasPermission('sys:menu:delete')")
    public void delete(@PathVariable Long menuId) {
        if (menuId <= Constant.SYS_MENU_MAX_ID) {
            throw new YadminException("系统菜单，不能删除");
        }
        //判断是否有子菜单或按钮
        List<SysMenu> menuList = sysMenuService.listChildrenMenuByParentId(menuId);
        if (menuList.size() > 0) {
            throw new YadminException("请先删除子菜单或按钮");
        }
        sysMenuService.deleteMenuAndRoleMenu(menuId);
    }


    /**
     * 验证参数是否正确
     *
     * @param menu
     */
    private void verifyForm(SysMenu menu) {

//        if (menu.getType() == MenuType.MENU.getValue()) {
//            if (StrUtil.isBlank(menu.getUrl())) {
//                throw new YadminException("菜单URL不能为空");
//            }
//        }
        if (Objects.equals(menu.getMenuId(), menu.getParentId())) {
            throw new YadminException("自己不能是自己的上级");
        }

//        //上级菜单类型
//        int parentType = MenuType.CATALOG.getValue();
//        if (menu.getParentId() != 0) {
//            SysMenu parentMenu = sysMenuService.getById(menu.getParentId());
//            parentType = parentMenu.getType();
//        }
//
//        //目录、菜单
//        if (menu.getType() == MenuType.CATALOG.getValue() ||
//                menu.getType() == MenuType.MENU.getValue()) {
//            if (parentType != MenuType.CATALOG.getValue()) {
//                throw new YadminException("上级菜单只能为目录类型");
//            }
//            return;
//        }

//        //按钮
//        if (menu.getType() == MenuType.BUTTON.getValue()) {
//            if (parentType != MenuType.MENU.getValue()) {
//                throw new YadminException("上级菜单只能为菜单类型");
//            }
//        }
    }
}
