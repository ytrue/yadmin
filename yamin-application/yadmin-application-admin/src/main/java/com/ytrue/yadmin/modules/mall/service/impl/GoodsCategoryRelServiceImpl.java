package com.ytrue.yadmin.modules.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.dao.mall.goods.GoodsCategoryRelDAO;
import com.ytrue.yadmin.model.mall.goods.GoodsCategoryRel;
import com.ytrue.yadmin.modules.mall.service.GoodsCategoryRelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ytrue
 * @date 2021/6/24 17:22
 * @description 商品与分类关系记录表
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsCategoryRelServiceImpl extends ServiceImpl<GoodsCategoryRelDAO, GoodsCategoryRel> implements GoodsCategoryRelService {


}
