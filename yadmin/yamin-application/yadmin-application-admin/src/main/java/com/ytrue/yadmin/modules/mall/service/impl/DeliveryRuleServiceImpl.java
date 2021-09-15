package com.ytrue.yadmin.modules.mall.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.dao.mall.setting.DeliveryRuleDAO;
import com.ytrue.yadmin.model.mall.setting.DeliveryRule;
import com.ytrue.yadmin.modules.mall.service.DeliveryRuleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author ytrue
 * @date 2021/09/08 17:22
 * @description 配送模板区域及运费
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DeliveryRuleServiceImpl extends ServiceImpl<DeliveryRuleDAO, DeliveryRule> implements DeliveryRuleService {


}
