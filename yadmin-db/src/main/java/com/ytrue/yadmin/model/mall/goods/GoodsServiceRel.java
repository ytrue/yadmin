package com.ytrue.yadmin.model.mall.goods;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author ytrue
 * @date 2021/6/24 17:22
 * @description 商品与服务承诺关系记录表
 */
@Data
@TableName("goods_service_rel")
public class GoodsServiceRel {
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
     * 服务承诺ID
     */
    @TableField("service_id")
    private Integer serviceId;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
}
