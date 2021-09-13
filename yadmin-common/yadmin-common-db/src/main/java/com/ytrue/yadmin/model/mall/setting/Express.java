package com.ytrue.yadmin.model.mall.setting;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author ytrue
 * @date 2021/9/13 17:22
 * @description 物流公司记录表
 */
@Data
@TableName("mall_express")
public class Express {
    /**
     * 物流公司ID
     */
    @TableId
    @TableField("express_id")
    private Integer expressId;
    /**
     * 物流公司名称
     */
    @TableField("express_name")
    private String expressName;
    /**
     * 物流公司编码
     */
    @TableField("code")
    private String code;
    /**
     * 排序(数字越大越靠前)
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
