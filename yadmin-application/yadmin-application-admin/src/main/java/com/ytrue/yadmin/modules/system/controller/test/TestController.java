package com.ytrue.yadmin.modules.system.controller.test;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ytrue
 * @date 2022/4/22 09:43
 * @description TestController
 */
@Api(tags = "测试用户")
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("test01")
    @ApiOperation("分页查询数据")
    public String test01() {
        return "test/test01";
    }

    @GetMapping("test02")
    @ApiOperation("分页查询数据")
    public String test02() {
        return "test/test02";
    }

    @GetMapping("test03")
    @ApiOperation("分页查询数据")
    public String test03() {
        return "test/test03";
    }
}
