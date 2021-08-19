package com.ytrue.yadmin.modules.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.dao.mall.goods.GoodsServiceDAO;
import com.ytrue.yadmin.model.mall.goods.GoodsPromise;
import com.ytrue.yadmin.modules.mall.service.GoodsPromiseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ytrue
 * @date 2021/6/24 17:22
 * @description 商品服务与承诺记录表
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsPromiseServiceImpl extends ServiceImpl<GoodsServiceDAO, GoodsPromise> implements GoodsPromiseService {


}
