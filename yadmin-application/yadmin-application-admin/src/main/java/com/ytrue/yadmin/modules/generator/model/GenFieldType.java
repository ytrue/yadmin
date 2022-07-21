package com.ytrue.yadmin.modules.generator.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * @author ytrue
 * @date 2022/6/13 17:59
 * @description 字段类型管理
 */
@Data
@TableName("gen_field_type")
@ApiModel(value = "字段类型管理")
@Accessors(chain = true)
public class GenFieldType implements Serializable {

	private static final long serialVersionUID = 6837054471436385670L;
	@TableId
	@ApiModelProperty(value = "id", required = true)
	private Long id;

	@TableField("column_type")
	@ApiModelProperty(value = "字段类型")
	private String columnType;

	@TableField("attr_type")
	@ApiModelProperty(value = "属性类型")
	private String attrType;

	@TableField("package_name")
	@ApiModelProperty(value = "属性包名")
	private String packageName;

	@TableField(value = "create_time", fill = FieldFill.INSERT)
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;

	@TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
	@ApiModelProperty(value = "更新时间")
	private LocalDateTime updateTime;
}
