package com.ytrue.yadmin.modules.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ytrue.yadmin.log.annotation.SysLog;

import com.ytrue.yadmin.modules.system.model.SysRole;
import com.ytrue.yadmin.modules.system.service.SysMenuService;
import com.ytrue.yadmin.modules.system.service.SysRoleService;
import com.ytrue.yadmin.search.SearchModel;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 角色管理
 */
@RestController
@RequestMapping("sys/role")
@AllArgsConstructor
public class SysRoleController {


    private final SysRoleService sysRoleService;

    private final SysMenuService sysMenuService;

    /**
     * 角色列表
     *
     * @param userSearchModel
     * @return
     */
    @PostMapping("page")
    @PreAuthorize("@pms.hasPermission('sys:role:page')")
    public IPage<SysRole> page(@RequestBody SearchModel<SysRole> userSearchModel) {
        return sysRoleService.page(userSearchModel.getPage(), userSearchModel.getQueryModel());
    }

    /**
     * 角色列表
     *
     * @return
     */
    @GetMapping("list")
    @PreAuthorize("@pms.hasPermission('sys:role:list')")
    public List<SysRole> list() {
        return sysRoleService.list();
    }

    /**
     * 角色信息
     *
     * @param roleId
     * @return
     */
    @GetMapping("{roleId}")
    @PreAuthorize("@pms.hasPermission('sys:role:info')")
    public SysRole info(@PathVariable("roleId") Long roleId) {
        SysRole role = sysRoleService.getById(roleId);
        //查询角色对应的菜单
        List<Long> menuList = sysMenuService.listMenuIdByRoleId(roleId);
        role.setMenuIdList(menuList);
        return role;
    }

    /**
     * 保存角色
     *
     * @param role
     */
    @SysLog("保存角色")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('sys:role:save')")
    public void save(@Validated @RequestBody SysRole role) {
        sysRoleService.saveRoleAndRoleMenu(role);

    }

    /**
     * 修改角色
     *
     * @param role
     */
    @SysLog("修改角色")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('sys:role:update')")
    public void update(@Validated @RequestBody SysRole role) {
        sysRoleService.updateRoleAndRoleMenu(role);
    }

    /**
     * 删除角色
     *
     * @param roleIds
     */
    @SysLog("删除角色")
    @DeleteMapping
    @PreAuthorize("@pms.hasPermission('sys:role:delete')")
    public void delete(@RequestBody Long[] roleIds) {
        sysRoleService.deleteBatch(roleIds);
    }
}
