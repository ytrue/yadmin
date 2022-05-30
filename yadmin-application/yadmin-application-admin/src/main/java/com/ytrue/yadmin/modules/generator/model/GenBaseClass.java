package com.ytrue.yadmin.modules.generator.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author ytrue
 * @date 2022/6/13 17:59
 * @description 基类管理
 */
@Data
@TableName("gen_base_class")
@ApiModel(value = "基类管理")
@Accessors(chain = true)
public class GenBaseClass  {


    @TableId
    @TableField("id")
    @ApiModelProperty(value = "id", required = true)
    private Long id;

    @TableField("package_name")
    @ApiModelProperty(value = "包名")
    private String packageName;

    @TableField("code")
    @ApiModelProperty(value = "基类编码")
    private String code;

    @TableField("code")
    @ApiModelProperty(value = "公共字段，多个用英文逗号分隔")
    private String fields;

    @TableField("remark")
    @ApiModelProperty(value = "备注")
    private String remark;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;


    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
}
