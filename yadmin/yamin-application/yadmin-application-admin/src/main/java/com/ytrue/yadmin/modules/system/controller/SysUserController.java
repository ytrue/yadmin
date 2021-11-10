package com.ytrue.yadmin.modules.system.controller;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ytrue.yadmin.log.annotation.SysLog;
import com.ytrue.yadmin.modules.system.model.SysUser;
import com.ytrue.yadmin.modules.system.model.vo.UserInfoVO;
import com.ytrue.yadmin.modules.system.service.SysRoleService;
import com.ytrue.yadmin.modules.system.service.SysUserService;
import com.ytrue.yadmin.search.SearchModel;
import com.ytrue.yadmin.security.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "系统用户")
@RestController
@RequestMapping("sys/user")
@AllArgsConstructor
public class SysUserController {

    private final SysUserService sysUserService;

    private final SysRoleService sysRoleService;

    private final JwtUtil jwtUtil;


    @PostMapping("page")
    @ApiOperation("分页查询")
    @PreAuthorize("@pms.hasPermission('sys:user:page')")
    public IPage<SysUser> page(@RequestBody SearchModel<SysUser> searchModel) {
        return sysUserService.page(searchModel.getPage(), searchModel.getQueryModel().lambda().orderByDesc(SysUser::getUserId));
    }


    @GetMapping("{userId}/info")
    @ApiOperation("用户信息")
    @PreAuthorize("@pms.hasPermission('sys:user:info')")
    public SysUser info(@PathVariable("userId") Long userId) {
        SysUser sysUser = sysUserService.getById(userId);
        Assert.notNull(sysUser, "数据不存在");
        List<Long> roleIdList = sysRoleService.listRoleIdByUserId(userId);
        sysUser.setRoleIdList(roleIdList);
        return sysUser;
    }


    @SysLog
    @PostMapping
    @ApiOperation("保存用户")
    @PreAuthorize("@pms.hasPermission('sys:user:save')")
    public void save(@Valid @RequestBody SysUser user) {
        sysUserService.saveUserAndUserRole(user);
    }

    @PutMapping
    @SysLog
    @ApiOperation("修改用户")
    @PreAuthorize("@pms.hasPermission('sys:user:update')")
    public void update(@Validated @RequestBody SysUser user) {
        sysUserService.updateUserAndUserRole(user);
    }

    @SysLog
    @DeleteMapping
    @ApiOperation("删除用户")
    @PreAuthorize("@pms.hasPermission('sys:user:delete')")
    public void delete(@RequestBody List<Long> userIds) {
        sysUserService.removeByIds(userIds);
    }


    @GetMapping("router")
    @ApiOperation("通过token获得我的基本信息")
    public UserInfoVO userInfo(HttpServletRequest request) {
        //获得token
        String header = request.getHeader("Authorization");
        String token = header.substring(header.indexOf("bearer") + 7);
        Claims claimsFromToken = jwtUtil.getClaimsFromToken(token);
        return sysUserService.getUserInfo(claimsFromToken);
    }
}
