package com.ytrue.yadmin.modules.system.controller;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ytrue.yadmin.contains.StrPool;
import com.ytrue.yadmin.log.annotation.SysLog;

import com.ytrue.yadmin.modules.system.model.SysRole;
import com.ytrue.yadmin.modules.system.service.SysMenuService;
import com.ytrue.yadmin.modules.system.service.SysRoleService;
import com.ytrue.yadmin.search.SearchModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

@Api(tags = "角色")
@RestController
@RequestMapping("sys/role")
@AllArgsConstructor
public class SysRoleController {


    private final SysRoleService sysRoleService;

    private final SysMenuService sysMenuService;

    @PostMapping("page")
    @ApiOperation("分页查询数据")
    @PreAuthorize("@pms.hasPermission('sys:role:page')")
    public IPage<SysRole> page(@RequestBody SearchModel<SysRole> userSearchModel) {
        return sysRoleService.page(userSearchModel.getPage(), userSearchModel.getQueryModel());
    }

    @GetMapping("list")
    @ApiOperation("所有角色列表")
    @PreAuthorize("@pms.hasPermission('sys:role:list')")
    public List<SysRole> list() {
        return sysRoleService.list();
    }

    @GetMapping("{roleId}/info")
    @ApiOperation("角色信息")
    @PreAuthorize("@pms.hasPermission('sys:role:info')")
    public SysRole info(@PathVariable("roleId") Long roleId) {
        SysRole role = sysRoleService.getById(roleId);
        Assert.notNull(role, StrPool.DATA_DOES_NOT_EXIST);
        //查询角色对应的菜单
        List<Long> menuList = sysMenuService.listMenuIdByRoleId(roleId);
        role.setMenuIdList(menuList);
        return role;
    }


    @SysLog
    @PostMapping
    @ApiOperation("保存角色")
    @PreAuthorize("@pms.hasPermission('sys:role:save')")
    public void save(@Validated @RequestBody SysRole role) {
        sysRoleService.saveRoleAndRoleMenu(role);
    }


    @SysLog
    @PutMapping
    @ApiOperation("修改角色")
    @PreAuthorize("@pms.hasPermission('sys:role:update')")
    public void update(@Validated @RequestBody SysRole role) {
        sysRoleService.updateRoleAndRoleMenu(role);
    }

    @SysLog
    @DeleteMapping
    @ApiOperation("删除角色")
    @PreAuthorize("@pms.hasPermission('sys:role:delete')")
    public void delete(@RequestBody Long[] roleIds) {
        sysRoleService.deleteBatch(roleIds);
    }
}
