package com.ytrue.yadmin.modules.mall.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.dao.mall.goods.GoodsCategoryDAO;
import com.ytrue.yadmin.enums.StrPool;
import com.ytrue.yadmin.model.mall.goods.GoodsCategory;
import com.ytrue.yadmin.modules.mall.service.GoodsCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ytrue
 * @date 2021/6/24 17:22
 * @description 商品分类表
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsCategoryServiceImpl extends ServiceImpl<GoodsCategoryDAO, GoodsCategory> implements GoodsCategoryService {

    @Override
    public void deleteGoodsCategory(List<Long> categoryIds) {
        //判断一下当前分类下是否有子分类
        categoryIds.forEach(id -> {
            GoodsCategory findGoodsCategoryByParentId = getOne(new QueryWrapper<GoodsCategory>()
                    .lambda()
                    .eq(GoodsCategory::getParentId, id), false);
            Assert.isNull(findGoodsCategoryByParentId, StrPool.SUBCLASS_EXISTS_AND_CANNOT_BE_DELETED.getMessage());
        });
        removeByIds(categoryIds);
    }
}
