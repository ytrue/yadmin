package com.ytrue.yadmin.modules.mall.controller;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ytrue.yadmin.enums.StrPool;
import com.ytrue.yadmin.log.annotation.SysLog;
import com.ytrue.yadmin.model.mall.goods.GoodsPromise;
import com.ytrue.yadmin.modules.mall.service.GoodsPromiseService;
import com.ytrue.yadmin.search.SearchModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author ytrue
 * @date 2021/8/19 11:37
 * @description 商品承诺
 */
@Api(tags = "商品承诺")
@RestController
@AllArgsConstructor
@RequestMapping("mall/goodsPromise")
public class GoodsPromiseController {

    private final GoodsPromiseService goodsPromiseService;

    @PostMapping("page")
    @ApiOperation("分页查询")
    public IPage<GoodsPromise> page(@RequestBody SearchModel<GoodsPromise> searchModel) {
        return goodsPromiseService
                .page(
                        searchModel.getPage(),
                        searchModel.getQueryModel()
                                .lambda()
                                .orderByDesc(GoodsPromise::getSort)
                );
    }


    @SysLog
    @PostMapping
    @ApiOperation("保存商品承诺")
    public void save(@Valid @RequestBody GoodsPromise goodsPromise) {
        goodsPromiseService.save(goodsPromise);
    }

    @GetMapping("{promiseId}/info")
    @ApiOperation("商品承诺信息")
    public GoodsPromise info(@PathVariable("promiseId") Long promiseId) {
        GoodsPromise goodsPromise = goodsPromiseService.getById(promiseId);
        Assert.notNull(goodsPromise, StrPool.DATA_DOES_NOT_EXIST.getMessage());
        return goodsPromise;
    }


    @SysLog
    @PutMapping
    @ApiOperation("修改商品承诺")
    public void update(@Valid @RequestBody GoodsPromise goodsPromise) {
        goodsPromiseService.updateById(goodsPromise);
    }

    @SysLog
    @DeleteMapping
    @ApiOperation("删除商品承诺")
    public void delete(@RequestBody List<Long> promiseIds) {
        goodsPromiseService.removeByIds(promiseIds);
    }
}
