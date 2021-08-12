package com.ytrue.yadmin.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ytrue
 * @date 2021/8/3 15:25
 * @description Setting
 */
@Data
@ApiModel(value = "系统设置")
@TableName("setting")
public class Setting {

    /**
     * 设置项标示
     */
    @TableField("`key`")
    @ApiModelProperty(value = "设置项标示", required = true)
    private String key;

    /**
     * 设置项描述
     */
    @TableField("`values`")
    @ApiModelProperty(value = "设置项描述", required = true)
    private String values;

    /**
     * 设置内容（json格式）
     */
    @TableField("`describe`")
    @ApiModelProperty(value = "设置内容（json格式")
    private String describe;
}
