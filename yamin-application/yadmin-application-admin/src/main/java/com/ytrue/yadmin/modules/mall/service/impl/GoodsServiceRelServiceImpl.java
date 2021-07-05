package com.ytrue.yadmin.modules.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.dao.mall.goods.GoodsServiceRelDAO;
import com.ytrue.yadmin.model.mall.goods.GoodsServiceRel;
import com.ytrue.yadmin.modules.mall.service.GoodsServiceRelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ytrue
 * @date 2021/6/24 17:22
 * @description 商品与服务承诺关系记录表
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsServiceRelServiceImpl extends ServiceImpl<GoodsServiceRelDAO, GoodsServiceRel> implements GoodsServiceRelService {

}
