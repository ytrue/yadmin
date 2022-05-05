package com.ytrue.yadmin.modules.system.controller.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ytrue.yadmin.core.utils.ResultData;
import com.ytrue.yadmin.modules.system.model.SysUser;
import com.ytrue.yadmin.modules.system.service.SysUserService;
import com.ytrue.yadmin.security.domain.User;
import com.ytrue.yadmin.security.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author ytrue
 * @date 2022/4/13 15:48
 * @description SysUserController
 */
@Api(tags = "系统用户")
@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class SysUserController {

    private final LoginService loginService;


    @Autowired
    private SysUserService sysUserService;


    @GetMapping("testuser")
    public List<UserDTO> list() {
        LambdaQueryWrapper<SysUser> select = new QueryWrapper<SysUser>().lambda().select(SysUser::getUserId, SysUser::getUsername);

        List<UserDTO> userDTOS = new ArrayList<>();
        List<SysUser> list = sysUserService.list(select);

        list.forEach(sysUser -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(sysUser.getUserId());
            userDTO.setUsername(sysUser.getUsername());

            userDTOS.add(userDTO);
        });


        return userDTOS;
    }


    @GetMapping("test01")
    @ApiOperation("分页查询数据")
    public String test01() {
        return "test01";
    }

    @GetMapping("test02")
    @ApiOperation("分页查询数据")
    public String test02() {
        return "test02";
    }

    @GetMapping("test03")
    @ApiOperation("分页查询数据")
    public String test03() {
        return "test03";
    }

    @RequestMapping("login")
    @ApiOperation("登录")
    public Map<String, String> login(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "email") String email) {

        User user = new User();
        user.setId(1)
                .setUsername(username)
                .setPassword(password)
                .setEmail(email);


        return loginService.login(user);
    }

    @GetMapping("logout")
    public ResultData<Object> logout() {
        loginService.logout();
        return ResultData.success();
    }
}
