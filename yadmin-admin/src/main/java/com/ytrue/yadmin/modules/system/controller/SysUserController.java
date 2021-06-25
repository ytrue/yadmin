package com.ytrue.yadmin.modules.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ytrue.yadmin.common.annotation.AutoValid;
import com.ytrue.yadmin.common.annotation.SysLog;
import com.ytrue.yadmin.common.annotation.WrapResp;
import com.ytrue.yadmin.common.search.SearchModel;
import com.ytrue.yadmin.common.utils.JwtUtils;
import com.ytrue.yadmin.model.system.SysUser;
import com.ytrue.yadmin.modules.system.service.SysMenuService;
import com.ytrue.yadmin.modules.system.service.SysRoleService;
import com.ytrue.yadmin.modules.system.service.SysUserService;

import com.ytrue.yadmin.modules.system.vo.UserInfoVO;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 系统用户控制器
 */
@Slf4j
@WrapResp
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
    @PostMapping("page")
    @PreAuthorize("@pms.hasPermission('sys:user:page')")
    public IPage<SysUser> page(@RequestBody SearchModel<SysUser> searchModel) {
        return sysUserService.page(searchModel.getPage(), searchModel.getQueryModel().orderByDesc("user_id"));
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
    @AutoValid(entity = SysUser.class)
    @PreAuthorize("@pms.hasPermission('sys:user:save')")
    public void save(@RequestBody SysUser user) {
        sysUserService.saveUserAndUserRole(user);
    }


    /**
     * 修改用户
     *
     * @param user
     */
    @SysLog("修改用户")
    @PutMapping
    @AutoValid(entity = SysUser.class)
    @PreAuthorize("@pms.hasPermission('sys:user:update')")
    public void update(@RequestBody SysUser user) {
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
