package com.ytrue.yadmin.model.mall.goods;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ytrue
 * @date 2021/6/24 17:22
 * @description 商品与分类关系记录表
 */
@Data
@TableName("goods_category_rel")
public class GoodsCategoryRel implements Serializable {
    private static final long serialVersionUID = 1678818822765036176L;
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
     * 商品分类ID
     */
    @TableField("category_id")
    private Integer categoryId;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
}
