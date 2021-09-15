package com.ytrue.yadmin.modules.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.dao.mall.goods.GoodsSpecValueDAO;
import com.ytrue.yadmin.model.mall.goods.GoodsSpecValue;
import com.ytrue.yadmin.modules.mall.service.GoodsSpecValueService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ytrue
 * @date 2021/6/24 17:22
 * @description 商品规格值记录表
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsSpecValueServiceImpl extends ServiceImpl<GoodsSpecValueDAO, GoodsSpecValue> implements GoodsSpecValueService {


}
