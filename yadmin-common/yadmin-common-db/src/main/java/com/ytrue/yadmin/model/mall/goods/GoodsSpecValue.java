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
 * @description 商品规格值记录表
 */
@Data
@TableName("goods_spec_value")
public class GoodsSpecValue  implements Serializable {
    private static final long serialVersionUID = 764414731500335320L;
    /**
     * 规格值ID
     */
    @TableId
    @TableField("spec_value_id")
    private Integer specValueId;
    /**
     * 规格值
     */
    @TableField("spec_value")
    private String specValue;
    /**
     * 规格组ID
     */
    @TableField("spec_id")
    private Integer specId;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
}
