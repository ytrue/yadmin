package com.ytrue.yadmin.model.mall.goods;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ytrue
 * @date 2021/6/24 17:22
 * @description 商品规格表
 */
@Data
@TableName("goods_sku")
public class GoodsSku implements Serializable {
    private static final long serialVersionUID = -470323858960001824L;
    /**
     * 记录ID
     */
    @TableId
    @TableField("id")
    private Integer id;
    /**
     * 商品sku唯一标识 (由规格id组成)
     */
    @TableField("goods_sku_id")
    private String goodsSkuId;
    /**
     * 商品ID
     */
    @TableField("goods_id")
    private Integer goodsId;
    /**
     * 规格图片ID
     */
    @TableField("image_id")
    private Integer imageId;
    /**
     * 商品sku编码
     */
    @TableField("goods_sku_no")
    private String goodsSkuNo;
    /**
     * 商品价格
     */
    @TableField("goods_price")
    private BigDecimal goodsPrice;
    /**
     * 商品划线价
     */
    @TableField("line_price")
    private BigDecimal linePrice;
    /**
     * 当前库存数量
     */
    @TableField("stock_num")
    private Integer stockNum;
    /**
     * 商品重量(Kg)
     */
    @TableField("goods_weight")
    private Double goodsWeight;
    /**
     * SKU的规格属性(json格式)
     */
    @TableField("goods_props")
    private String goodsProps;
    /**
     * 规格值ID集(json格式)
     */
    @TableField("spec_value_ids")
    private String specValueIds;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
}
