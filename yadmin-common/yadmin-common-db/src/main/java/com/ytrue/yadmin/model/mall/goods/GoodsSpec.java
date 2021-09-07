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
 * @description 商品规格组记录表
 */
@Data
@TableName("goods_spec")
public class GoodsSpec implements Serializable {
    private static final long serialVersionUID = 6182920975164060346L;
    /**
     * 规格组ID
     */
    @TableId
    @TableField("spec_id")
    private Integer specId;
    /**
     * 规格组名称
     */
    @TableField("spec_name")
    private String specName;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
}
