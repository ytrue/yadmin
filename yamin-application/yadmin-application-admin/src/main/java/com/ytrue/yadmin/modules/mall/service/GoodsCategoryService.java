package com.ytrue.yadmin.modules.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ytrue.yadmin.model.mall.goods.GoodsCategory;

import java.util.List;

/**
 * @author ytrue
 * @date 2021/6/24 17:22
 * @description 商品分类表
 */
public interface GoodsCategoryService extends IService<GoodsCategory> {

    /**
     * 删除商品分类
     *
     * @param categoryIds
     */
    void deleteGoodsCategory(List<Long> categoryIds);

}
