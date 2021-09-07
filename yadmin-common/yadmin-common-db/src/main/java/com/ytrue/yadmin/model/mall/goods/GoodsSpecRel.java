package com.ytrue.yadmin.model.mall.goods;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ytrue
 * @date 2021/6/24 17:22
 * @description 商品与规格值关系记录表
 */
@Data
@TableName("goods_spec_rel")
public class GoodsSpecRel implements Serializable {
    private static final long serialVersionUID = -805479633775154332L;
    /**
     * 主键ID
     */
    @TableId
    @TableField("id")
    private Integer id;
    /**
     * 商品ID
     */
    @TableField("goods_id")
    private Integer goodsId;
    /**
     * 规格组ID
     */
    @TableField("spec_id")
    private Integer specId;
    /**
     * 规格值ID
     */
    @TableField("spec_value_id")
    private Integer specValueId;
    /**
     * 商城ID
     */
    @TableField("store_id")
    private Integer storeId;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Integer createTime;
}
