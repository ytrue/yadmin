package com.ytrue.yadmin.modules.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.dao.mall.goods.GoodsCategoryDAO;
import com.ytrue.yadmin.model.mall.goods.GoodsCategory;
import com.ytrue.yadmin.modules.mall.service.GoodsCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ytrue
 * @date 2021/6/24 17:22
 * @description 商品分类表
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsCategoryServiceImpl extends ServiceImpl<GoodsCategoryDAO, GoodsCategory> implements GoodsCategoryService {
}
