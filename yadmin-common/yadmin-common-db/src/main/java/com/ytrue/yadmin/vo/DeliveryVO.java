package com.ytrue.yadmin.vo;

import com.ytrue.yadmin.model.mall.setting.DeliveryRule;
import lombok.Data;

import java.util.List;

/**
 * @author ytrue
 * @date 2021/9/8 10:57
 * @description 运费模板添加修改详情，接受和返回
 */

@Data
public class DeliveryVO {
    private static final long serialVersionUID = 864792229608151497L;

    /**
     * 模板ID
     */
    private Integer deliveryId;
    /**
     * 模板名称
     */
    private String name;
    /**
     * 计费方式(10按件数 20按重量)
     */
    private Integer method;
    /**
     * 排序方式(数字越小越靠前)
     */
    private Integer sort;

    /**
     * 规则集合
     */
    private List<DeliveryRule> rules;
}
