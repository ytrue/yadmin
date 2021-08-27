package com.ytrue.yadmin.modules.mall.controller;

import cn.hutool.core.lang.Assert;
import com.ytrue.yadmin.enums.StrPool;
import com.ytrue.yadmin.log.annotation.SysLog;
import com.ytrue.yadmin.model.mall.goods.GoodsCategory;
import com.ytrue.yadmin.modules.mall.service.GoodsCategoryService;
import com.ytrue.yadmin.search.SearchModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author ytrue
 * @date 2021/8/19 11:34
 * @description 商品分类列表
 */
@Api(tags = "商品分类")
@RestController
@AllArgsConstructor
@RequestMapping("mall/goodsCategory")
public class GoodsCategoryController {

    private final GoodsCategoryService goodsCategoryService;

    @PostMapping("list")
    @ApiOperation("商品分类列表")
    public List<GoodsCategory> list(@RequestBody SearchModel<GoodsCategory> searchModel) {
        return goodsCategoryService.list(searchModel.getQueryModel().lambda().orderByDesc(GoodsCategory::getSort));
    }


    @SysLog
    @PostMapping
    @ApiOperation("保存商品分类")
    public void save(@Valid @RequestBody GoodsCategory category) {
        goodsCategoryService.save(category);
    }

    @GetMapping("{categoryId}/info")
    @ApiOperation("商品分类信息")
    public GoodsCategory info(@PathVariable("categoryId") Long categoryId) {
        GoodsCategory category = goodsCategoryService.getById(categoryId);
        Assert.notNull(category, StrPool.DATA_DOES_NOT_EXIST.getMessage());
        return category;
    }

    @SysLog
    @PutMapping
    @ApiOperation("修改商品分类")
    public void update(@Valid @RequestBody GoodsCategory category) {
        goodsCategoryService.updateById(category);
    }

    @SysLog
    @DeleteMapping
    @ApiOperation("删除商品分类")
    public void delete(@RequestBody List<Long> categoryIds) {
        goodsCategoryService.deleteGoodsCategory(categoryIds);
    }
}
