package com.ytrue.yadmin.modules.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;


import com.ytrue.yadmin.log.annotation.SysLog;
import com.ytrue.yadmin.modules.system.model.SysUser;
import com.ytrue.yadmin.modules.system.service.SysMenuService;
import com.ytrue.yadmin.modules.system.service.SysRoleService;
import com.ytrue.yadmin.modules.system.service.SysUserService;
import com.ytrue.yadmin.modules.system.model.vo.UserInfoVO;
import com.ytrue.yadmin.search.SearchModel;
import com.ytrue.yadmin.security.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 系统用户控制器
 */
@Slf4j
@RestController
@RequestMapping("sys/user")
@AllArgsConstructor
public class SysUserController {

    private final SysUserService sysUserService;

    private final SysRoleService sysRoleService;

    private final SysMenuService sysMenuService;

    private final JwtUtils jwtUtils;

    /**
     * 所有用户列表
     *
     * @param searchModel
     * @return
     */
    @SysLog("查询用户")
    @PostMapping("page")
    @PreAuthorize("@pms.hasPermission('sys:user:page')")
    public IPage<SysUser> page(@RequestBody SearchModel<SysUser> searchModel) {
        return sysUserService.page(searchModel.getPage(), searchModel.getQueryModel().lambda().orderByDesc(SysUser::getUserId));
    }


    /**
     * 用户信息
     *
     * @param userId
     * @return
     */
    @GetMapping("{userId}")
    @PreAuthorize("@pms.hasPermission('sys:user:info')")
    public SysUser info(@PathVariable("userId") Long userId) {
        SysUser sysUser = sysUserService.getById(userId);
        List<Long> roleIdList = sysRoleService.listRoleIdByUserId(userId);
        sysUser.setRoleIdList(roleIdList);
        return sysUser;
    }

    /**
     * 保存用户
     *
     * @param user
     */
    @SysLog("保存用户")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('sys:user:save')")
    public void save(@Valid @RequestBody SysUser user) {

        System.out.println("ok");
        // sysUserService.saveUserAndUserRole(user);
    }


    /**
     * 修改用户
     *
     * @param user
     */
    @SysLog("修改用户")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('sys:user:update')")
    public void update(@Validated @RequestBody SysUser user) {
        sysUserService.updateUserAndUserRole(user);
    }

    /**
     * 删除用户
     *
     * @param userIds
     */
    @SysLog("删除用户")
    @DeleteMapping
    @PreAuthorize("@pms.hasPermission('sys:user:delete')")
    public void delete(@RequestBody List<Long> userIds) {
        sysUserService.removeByIds(userIds);
    }


    /**
     * 获得我的基本信息
     *
     * @param request
     * @return
     */
    @GetMapping("router")
    public UserInfoVO userInfo(HttpServletRequest request) {
        //获得token
        String header = request.getHeader("Authorization");
        String token = header.substring(header.indexOf("bearer") + 7);
        Claims claimsFromToken = jwtUtils.getClaimsFromToken(token);
        return sysUserService.getUserInfo(claimsFromToken);
    }
}
