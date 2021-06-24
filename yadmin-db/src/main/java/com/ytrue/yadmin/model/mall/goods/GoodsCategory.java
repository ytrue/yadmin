package com.ytrue.yadmin.model.mall.goods;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author ytrue
 * @date 2021/6/24 17:22
 * @description 商品分类表
 */
@Data
@TableName("goods_category")
public class GoodsCategory {
    /**
     * 商品分类ID
     */
    @TableId
    @TableField("category_id")
    private Integer categoryId;
    /**
     * 分类名称
     */
    @TableField("name")
    private String name;
    /**
     * 上级分类ID
     */
    @TableField("parent_id")
    private Integer parentId;
    /**
     * 分类图片ID
     */
    @TableField("image")
    private String image;
    /**
     * 状态(1显示 0隐藏)
     */
    @TableField("status")
    private Integer status;
    /**
     * 排序方式(数字越小越靠前)
     */
    @TableField("sort")
    private Integer sort;
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
