package com.ytrue.yadmin.model.mall.goods;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author ytrue
 * @date 2021/6/24 17:22
 * @description 商品服务与承诺记录表
 */
@Data
@TableName("goods_service")
public class GoodsPromise implements Serializable {
    private static final long serialVersionUID = 4423496623568406504L;
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
    private Boolean isDefault;
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
     * 是否删除(1已删除)
     */
    @TableField("is_delete")
    private Integer isDelete;
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
