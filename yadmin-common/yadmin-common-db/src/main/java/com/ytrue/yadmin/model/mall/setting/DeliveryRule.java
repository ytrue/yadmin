package com.ytrue.yadmin.model.mall.setting;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author ytrue
 * @date 2021/09/08 17:22
 * @description 配送模板区域及运费表
 */
@Data
@TableName("mall_delivery_rule")
public class DeliveryRule implements Serializable {
    private static final long serialVersionUID = -9077448229680116528L;
    /**
     * 规则ID
     */
    @TableId
    @TableField("rule_id")
    private Integer ruleId;
    /**
     * 配送模板ID
     */
    @TableField("delivery_id")
    private Integer deliveryId;
    /**
     * 可配送区域(城市id集)
     */
    @TableField("region")
    private String region;
    /**
     * 可配送区域(文字展示)
     */
    @TableField("region_text")
    private String regionText;
    /**
     * 首件(个)/首重(Kg)
     */
    @TableField("first")
    private Double first;
    /**
     * 运费(元)
     */
    @TableField("first_fee")
    private BigDecimal firstFee;
    /**
     * 续件/续重
     */
    @TableField("additional")
    private Double additional;
    /**
     * 续费(元)
     */
    @TableField("additional_fee")
    private BigDecimal additionalFee;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

}
