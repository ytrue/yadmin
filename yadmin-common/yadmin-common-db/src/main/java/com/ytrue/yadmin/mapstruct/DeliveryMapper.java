package com.ytrue.yadmin.mapstruct;

import com.ytrue.yadmin.base.BaseMapper;
import com.ytrue.yadmin.model.mall.setting.Delivery;
import com.ytrue.yadmin.vo.DeliveryVO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author ytrue
 * @date 2021/9/8 14:00
 * @description Delivery，DeliveryVO 相互转化
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeliveryMapper extends BaseMapper<DeliveryVO, Delivery> {
}
