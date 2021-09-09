package com.ytrue.yadmin.modules.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.dao.mall.setting.DeliveryDAO;
import com.ytrue.yadmin.dao.mall.setting.DeliveryRuleDAO;
import com.ytrue.yadmin.mapstruct.DeliveryMapper;
import com.ytrue.yadmin.model.mall.setting.Delivery;
import com.ytrue.yadmin.model.mall.setting.DeliveryRule;
import com.ytrue.yadmin.modules.mall.service.DeliveryService;
import com.ytrue.yadmin.vo.DeliveryVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Consumer;

/**
 * @author ytrue
 * @date 2021/09/08 17:22
 * @description 配送模板
 */
@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class DeliveryServiceImpl extends ServiceImpl<DeliveryDAO, Delivery> implements DeliveryService {

    private final DeliveryRuleDAO deliveryRuleDAO;

    private final DeliveryMapper deliveryMapper;

    private final DeliveryDAO deliveryDAO;


    @Override
    public void saveDelivery(DeliveryVO deliveryVO) {
        Delivery delivery = deliveryMapper.toEntity(deliveryVO);
        save(delivery);
        deliveryVO.getRules().forEach(deliveryRule -> {
            deliveryRule.setDeliveryId(delivery.getDeliveryId());
            deliveryRuleDAO.insert(deliveryRule);
        });
    }

    @Override
    public void updateDelivery(DeliveryVO deliveryVO) {
        updateById(deliveryMapper.toEntity(deliveryVO));
        deliveryRuleDAO.delete(new LambdaQueryWrapper<DeliveryRule>().eq(DeliveryRule::getDeliveryId, deliveryVO.getDeliveryId()));
        deliveryVO.getRules().forEach(deliveryRule -> {
            deliveryRule.setDeliveryId(deliveryVO.getDeliveryId());
            deliveryRuleDAO.insert(deliveryRule);
        });
    }

    @Override
    public DeliveryVO getDeliveryDetailsById(Long deliveryId) {
        return deliveryDAO.getDeliveryDetailsById(deliveryId);
    }

}
