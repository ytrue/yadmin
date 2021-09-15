package com.ytrue.yadmin.model.mall.setting;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ytrue
 * @date 2021/09/08 17:22
 * @description 配送模板主表
 */
@Data
@TableName("mall_delivery")
public class Delivery implements Serializable {
    private static final long serialVersionUID = -1441052989254513984L;
    /**
     * 模板ID
     */
    @TableId(value = "delivery_id",type = IdType.AUTO)
    private Integer deliveryId;
    /**
     * 模板名称
     */
    @TableField("name")
    private String name;
    /**
     * 计费方式(10按件数 20按重量)
     */
    @TableField("method")
    private Integer method;
    /**
     * 排序方式(数字越小越靠前)
     */
    @TableField("sort")
    private Integer sort;
    /**
     * 是否删除
     */
    @TableId
    @TableLogic
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
