package com.ytrue.yadmin.modules.mall.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ytrue.yadmin.log.annotation.SysLog;
import com.ytrue.yadmin.model.mall.goods.Goods;
import com.ytrue.yadmin.modules.mall.service.GoodsService;
import com.ytrue.yadmin.search.SearchModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    private final GoodsService goodsService;


    @PostMapping("page")
    @ApiOperation("分页查询")
    public IPage<Goods> page(@RequestBody SearchModel<Goods> searchModel) {
        return goodsService.page(
                searchModel.getPage(),
                searchModel.getQueryModel()
                        .lambda()
                        //.select(Goods::getGoodsId,Goods::getSort)
                        .orderByDesc(Goods::getSort)
                        .orderByDesc(Goods::getGoodsId)
        );
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
    public void delete(@RequestBody List<Long> goodsIds) {
        goodsService.removeByIds(goodsIds);
    }
}
