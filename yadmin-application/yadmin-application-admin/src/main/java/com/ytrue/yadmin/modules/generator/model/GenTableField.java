package com.ytrue.yadmin.modules.generator.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * @author ytrue
 * @date 2022/6/13 17:59
 * @description 表字段信息
 */
@Data
@TableName("gen_table_field")
public class GenTableField implements Serializable {

    private static final long serialVersionUID = 2875848782252281735L;
    @TableId
    @ApiModelProperty(value = "id", required = true)
    private Long id;

    @TableField("table_id")
    @ApiModelProperty(value = "表ID")
    private Long tableId;

    @TableField("table_name")
    @ApiModelProperty(value = "表名")
    private String tableName;

    @TableField("column_name")
    @ApiModelProperty(value = "列名")
    private String columnName;

    @TableField("column_type")
    @ApiModelProperty(value = "类型")
    private String columnType;

    @TableField("column_comment")
    @ApiModelProperty(value = "列说明")
    private String columnComment;

    @TableField(exist = false)
    @ApiModelProperty(value = "列说明")
    private String comment;

    @TableField("attr_name")
    @ApiModelProperty(value = "属性名")
    private String attrName;

    @TableField("attr_type")
    @ApiModelProperty(value = "属性类型")
    private String attrType;

    @TableField("package_name")
    @ApiModelProperty(value = "属性包名")
    private String packageName;

    @TableField("is_pk")
    @ApiModelProperty(value = "是否主键 0：否  1：是")
    private Boolean isPk;

    @TableField("is_required")
    @ApiModelProperty(value = "是否必填 0：否  1：是")
    private Boolean isRequired;

    @TableField("is_form")
    @ApiModelProperty(value = "是否表单字段 0：否  1：是")
    private Boolean isForm;

    @TableField("is_list")
    @ApiModelProperty(value = "是否列表字段 0：否  1：是")
    private Boolean isList;

    @TableField("is_query")
    @ApiModelProperty(value = "是否查询字段 0：否  1：是")
    private Boolean isQuery;

    @TableField("query_type")
    @ApiModelProperty(value = "查询方式")
    private String queryType;

    @TableField("form_type")
    @ApiModelProperty(value = "表单类型")
    private String formType;

    @TableField("dict_name")
    @ApiModelProperty(value = "字典名称")
    private String dictName;

    @TableField("validator_type")
    @ApiModelProperty(value = "效验方式")
    private String validatorType;

    @TableField("sort")
    @ApiModelProperty(value = "排序")
    private Integer sort;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
}
