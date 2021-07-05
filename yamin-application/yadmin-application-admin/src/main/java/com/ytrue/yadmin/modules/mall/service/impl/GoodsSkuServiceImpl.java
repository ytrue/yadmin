package com.ytrue.yadmin.modules.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.dao.mall.goods.GoodsSkuDAO;
import com.ytrue.yadmin.model.mall.goods.GoodsSku;
import com.ytrue.yadmin.modules.mall.service.GoodsSkuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ytrue
 * @date 2021/6/24 17:22
 * @description 商品规格表
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsSkuServiceImpl extends ServiceImpl<GoodsSkuDAO, GoodsSku> implements GoodsSkuService {


}
