package com.ytrue.yadmin.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ytrue
 * @date 2021/8/3 15:25
 * @description Setting
 */
@Data
@ApiModel(value = "系统设置")
@TableName("setting")
public class Setting implements Serializable {

    private static final long serialVersionUID = -5683203397642102900L;

    /**
     * 设置项标示
     */
    @TableField("`key`")
    @ApiModelProperty(value = "设置项标示", required = true)
    private String key;

    /**
     * 设置内容（json格式）
     */
    @TableField("`values`")
    @ApiModelProperty(value = "设置内容（json格式）", required = true)
    private String values;

    /**
     * 设置项描述
     */
    @TableField("`describe`")
    @ApiModelProperty(value = "设置内容（json格式")
    private String describe;
}
