package com.ytrue.yadmin.modules.system.controller.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ytrue
 * @date 2022/4/13 15:48
 * @description SysUserController
 */
@Api(tags = "系统用户")
@RestController
@RequestMapping("user")
public class SysUserController {


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
}
