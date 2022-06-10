package com.ytrue.yadmin.modules.generator.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author ytrue
 * @date 2022/5/27 17:00
 * @description TableFieldDTO
 */
@Data
@Accessors(chain = true)
public class TableFieldDTO implements Serializable {
    private static final long serialVersionUID = 6628863576207565855L;

    @ApiModelProperty(value = "表ID")
    private Long tableId;

    @ApiModelProperty(value = "表名")
    private String tableName;

    @ApiModelProperty(value = "列名")
    private String columnName;

    @ApiModelProperty(value = "类型")
    private String columnType;

    @ApiModelProperty(value = "列说明")
    private String columnComment;

    @ApiModelProperty(value = "是否主键 0：否  1：是")
    private Boolean isPk;
}
