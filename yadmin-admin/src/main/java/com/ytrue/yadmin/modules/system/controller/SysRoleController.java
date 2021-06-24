package com.ytrue.yadmin.modules.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ytrue.yadmin.common.annotation.AutoValid;
import com.ytrue.yadmin.common.annotation.WrapResp;
import com.ytrue.yadmin.common.search.SearchModel;
import com.ytrue.yadmin.common.annotation.SysLog;
import com.ytrue.yadmin.model.system.SysRole;
import com.ytrue.yadmin.modules.system.service.SysMenuService;
import com.ytrue.yadmin.modules.system.service.SysRoleService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 角色管理
 */
@WrapResp
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
    @AutoValid(entity = SysRole.class)
    @PreAuthorize("@pms.hasPermission('sys:role:save')")
    public void save(@RequestBody SysRole role) {
        sysRoleService.saveRoleAndRoleMenu(role);

    }

    /**
     * 修改角色
     *
     * @param role
     */
    @SysLog("修改角色")
    @PutMapping
    @AutoValid(entity = SysRole.class)
    @PreAuthorize("@pms.hasPermission('sys:role:update')")
    public void update(@RequestBody SysRole role) {
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