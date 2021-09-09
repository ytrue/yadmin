package com.ytrue.yadmin.modules.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ytrue.yadmin.model.mall.setting.Delivery;
import com.ytrue.yadmin.vo.DeliveryVO;


/**
 * @author ytrue
 * @date 2021/09/08 17:22
 * @description 配送模板
 */
public interface DeliveryService extends IService<Delivery> {


    /**
     * 保存配送模板
     *
     * @param deliveryVO
     */
    void saveDelivery(DeliveryVO deliveryVO);

    /**
     * 编辑配送模板
     *
     * @param deliveryVO
     */
    void updateDelivery(DeliveryVO deliveryVO);


    /**
     *根据deliveryId 获得  Delivery 和 DeliveryRule 一对多的数据
     *
     * @param deliveryId
     * @return
     */
    DeliveryVO getDeliveryDetailsById(Long deliveryId);
}
