package com.ytrue.yadmin.model.mall.goods;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ytrue
 * @date 2021/6/24 17:22
 * @description 商品记录表
 */
@Data
@TableName("goods")
public class Goods implements Serializable {
    private static final long serialVersionUID = -8683232630198296482L;
    /**
     * 商品ID
     */
    @TableId
    @TableField("goods_id")
    private Integer goodsId;
    /**
     * 商品名称
     */
    @TableField("goods_name")
    private String goodsName;
    /**
     * 商品图片
     */
    @TableField("goods_images")
    private String goodsImages;
    /**
     * 商品编码
     */
    @TableField("goods_no")
    private String goodsNo;
    /**
     * 商品卖点
     */
    @TableField("selling_point")
    private String sellingPoint;
    /**
     * 商品规格(10单规格 20多规格)
     */
    @TableField("spec_type")
    private Integer specType;
    /**
     * 商品价格(最低)
     */
    @TableField("goods_price_min")
    private BigDecimal goodsPriceMin;
    /**
     * 商品价格(最高)
     */
    @TableField("goods_price_max")
    private BigDecimal goodsPriceMax;
    /**
     * 划线价格(最低)
     */
    @TableField("line_price_min")
    private BigDecimal linePriceMin;
    /**
     * 划线价格(最高)
     */
    @TableField("line_price_max")
    private BigDecimal linePriceMax;
    /**
     * 库存总量(包含所有sku)
     */
    @TableField("stock_total")
    private Integer stockTotal;
    /**
     * 商品详情
     */
    @TableField("content")
    private String content;
    /**
     * 初始销量
     */
    @TableField("sales_initial")
    private Integer salesInitial;
    /**
     * 实际销量
     */
    @TableField("sales_actual")
    private Integer salesActual;
    /**
     * 配送模板ID
     */
    @TableField("delivery_id")
    private Integer deliveryId;
    /**
     * 商品状态(10上架 20下架)
     */
    @TableField("status")
    private Integer status;
    /**
     * 排序(数字越小越靠前)
     */
    @TableField("sort")
    private Integer sort;
    /**
     * 是否删除
     */
    @TableLogic
    @TableField("is_delete")
    private Integer isDelete;
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
