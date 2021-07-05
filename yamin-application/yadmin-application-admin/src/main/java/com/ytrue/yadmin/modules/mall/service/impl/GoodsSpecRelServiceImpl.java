package com.ytrue.yadmin.modules.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.dao.mall.goods.GoodsSpecRelDAO;
import com.ytrue.yadmin.model.mall.goods.GoodsSpecRel;
import com.ytrue.yadmin.modules.mall.service.GoodsSpecRelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ytrue
 * @date 2021/6/24 17:22
 * @description 商品与规格值关系记录表
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsSpecRelServiceImpl extends ServiceImpl<GoodsSpecRelDAO, GoodsSpecRel> implements GoodsSpecRelService {


}
