package com.ytrue.yadmin.modules.mall.controller;

import com.ytrue.yadmin.log.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author ytrue
 * @date 2021/8/19 11:36
 * @description 商品管理
 */
@Api(tags = "商品管理")
@RestController
@AllArgsConstructor
@RequestMapping("mall/goods")
public class GoodsController {


    @PostMapping("page")
    @ApiOperation("分页查询")
    public void page() {

    }


    @SysLog
    @PostMapping
    @ApiOperation("保存商品")
    public void save() {

    }

    @GetMapping("{goodsId}/info")
    @ApiOperation("商品信息")
    public void info() {

    }


    @SysLog
    @PutMapping
    @ApiOperation("修改商品")
    public void update() {

    }

    @SysLog
    @DeleteMapping
    @ApiOperation("删除商品")
    public void delete() {

    }
}
