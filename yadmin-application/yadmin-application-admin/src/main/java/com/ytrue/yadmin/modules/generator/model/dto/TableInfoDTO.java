package com.ytrue.yadmin.modules.generator.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author ytrue
 * @date 2022/5/27 15:51
 * @description TableInfoVO
 */
@Data
@Accessors(chain = true)
public class TableInfoDTO implements Serializable {

    private static final long serialVersionUID = 302845831542693685L;

    @ApiModelProperty(value = "表名")
    private String tableName;

    @ApiModelProperty(value = "实体类名称")
    private String className;


    @ApiModelProperty(value = "功能名")
    private String tableComment;

    @ApiModelProperty(value = "数据源ID")
    private Long datasourceId;
}
