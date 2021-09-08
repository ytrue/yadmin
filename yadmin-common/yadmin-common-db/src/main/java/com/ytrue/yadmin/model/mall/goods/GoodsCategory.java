package com.ytrue.yadmin.model.mall.goods;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author ytrue
 * @date 2021/6/24 17:22
 * @description 商品分类表
 */
@Data
@TableName("mall_goods_category")
public class GoodsCategory implements Serializable {
    private static final long serialVersionUID = 5254014488659406254L;
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
     * 分类图片ID,改空值,把字段修改成空
     */
    @TableField(value = "image", updateStrategy = FieldStrategy.IGNORED)
    private String image;
    /**
     * 状态(1显示 0隐藏)
     */
    @TableField("status")
    private Boolean status;
    /**
     * 排序方式(数字越小越靠前)
     */
    @TableField("sort")
    private Integer sort;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
