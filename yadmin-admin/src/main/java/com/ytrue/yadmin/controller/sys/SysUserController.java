package com.ytrue.yadmin.controller.sys;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ytrue.yadmin.common.annotation.AutoValid;
import com.ytrue.yadmin.common.annotation.WrapResp;
import com.ytrue.yadmin.common.exeption.YadminException;
import com.ytrue.yadmin.common.response.ResponseData;
import com.ytrue.yadmin.common.search.SearchModel;
import com.ytrue.yadmin.common.annotation.SysLog;
import com.ytrue.yadmin.sys.model.SysUser;
import com.ytrue.yadmin.sys.service.SysRoleService;
import com.ytrue.yadmin.sys.service.SysUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 系统用户控制器
 */
@Slf4j
@WrapResp
@RestController
@RequestMapping("/sys/user")
@AllArgsConstructor
public class SysUserController {


    private final SysUserService sysUserService;
    private final SysRoleService sysRoleService;


    /**
     * 所有用户列表
     *
     * @param searchModel
     * @return
     */
    @PostMapping("/page")
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
    @GetMapping("/info/{userId}")
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
        String username = user.getUsername();
        SysUser dbUser = sysUserService.getOne(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUsername, username));
        if (dbUser != null) {
            throw new YadminException("该用户已存在");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
        String password = user.getPassword();
        SysUser dbUserNameInfo = sysUserService.getOne(new QueryWrapper<SysUser>().eq("username", user.getUsername()));
        if (dbUserNameInfo != null && !Objects.equals(dbUserNameInfo.getUserId(), user.getUserId())) {
            throw new YadminException("该用户已存在");
        }
        if (StrUtil.isBlank(password)) {
            user.setPassword(null);
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
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
        if (userIds.size() == 0) {
            throw new YadminException("请选择需要删除的用户");
        }
        if (userIds.contains(1L)) {
            throw new YadminException("系统管理员不能删除");
        }
//        if (userIds.contains(Convert.toLong(jwtParseToken.getUserInfo().get("userId")))) {
//            return ResponseData.fail("当前用户不能删除");
//        }
        sysUserService.removeByIds(userIds);
    }


    /**
     * 获得当前用户的就基本信息和路由信息
     *
     * @return
     */
    @PostMapping("/info")
    public ResponseData<Map<String, Object>> getUserInfo(HttpServletRequest request) {
        //获得token
        //查看什么什么的
        String header = request.getHeader("Authorization");
        String token = header.substring(header.indexOf("bearer") + 7);

        //获得用户
        SysUser user = sysUserService.getById(1);
        //获得用户的角色
        return null;
    }

    /**
     * 获得我的路由，这里先模拟
     *
     * @return---tmp
     */
    @GetMapping("router")
    public Object getMyRouter() {
       return  ResponseData.success();
    }
}
