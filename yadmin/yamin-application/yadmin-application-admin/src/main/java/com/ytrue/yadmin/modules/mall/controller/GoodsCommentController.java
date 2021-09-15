package com.ytrue.yadmin.modules.mall.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ytrue
 * @date 2021/8/19 11:45
 * @description 商品评价
 */
@Api(tags = "商品评价")
@RestController
@AllArgsConstructor
@RequestMapping("mall/goodsComment")
public class GoodsCommentController {


    @PostMapping("page")
    @ApiOperation("分页查询")
    public void page() {

    }

}
