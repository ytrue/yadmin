package com.ytrue.yadmin.dao.mall.setting;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ytrue.yadmin.model.mall.setting.Delivery;
import com.ytrue.yadmin.vo.DeliveryVO;
import org.apache.ibatis.annotations.Param;


/**
 * @author ytrue
 * @date 2021/09/08 17:22
 * @description 配送模板主表
 */
public interface DeliveryDAO extends BaseMapper<Delivery> {
    /**
     * 根据deliveryId 获得  Delivery 和 DeliveryRule 一对多的数据
     * 这个注解不支持一对多嵌套结果，只支持嵌套sql，所以使用xml配置
     *
     * @param deliveryId
     * @return
     */
    DeliveryVO getDeliveryDetailsById(@Param("deliveryId") Long deliveryId);
}
