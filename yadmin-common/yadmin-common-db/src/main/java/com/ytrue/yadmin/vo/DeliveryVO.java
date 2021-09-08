package com.ytrue.yadmin.vo;

import com.ytrue.yadmin.model.mall.setting.Delivery;
import com.ytrue.yadmin.model.mall.setting.DeliveryRule;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author ytrue
 * @date 2021/9/8 10:57
 * @description 运费模板添加修改详情，接受和返回
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class DeliveryVO extends Delivery {
    private static final long serialVersionUID = 864792229608151497L;

    /**
     * 规则集合
     */
    private List<DeliveryRule> rules;
}
