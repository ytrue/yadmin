package com.ytrue.yadmin.model.mall.goods;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author ytrue
 * @date 2021/6/24 17:22
 * @description 商品服务与承诺记录表
 */
@Data
@TableName("goods_service")
public class GoodsPromise {
    /**
     * 商品服务ID
     */
    @TableId
    @TableField("service_id")
    private Integer serviceId;
    /**
     * 服务名称
     */
    @TableField("name")
    private String name;
    /**
     * 概述
     */
    @TableField("summary")
    private String summary;
    /**
     * 是否默认(新增商品时)
     */
    @TableField("is_default")
    private Integer isDefault;
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
     * 是否删除(1已删除)
     */
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
