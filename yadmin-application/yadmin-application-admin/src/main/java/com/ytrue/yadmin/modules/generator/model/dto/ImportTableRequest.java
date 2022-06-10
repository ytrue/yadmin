package com.ytrue.yadmin.modules.generator.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ytrue
 * @date 2022/6/10 14:48
 * @description 导入表的请求参数
 */
@Data
@ApiModel(value = "导入表的请求参数")
public class ImportTableRequest {

    @ApiModelProperty(value = "数据源id", required = true)
    private Long datasourceId;

    @ApiModelProperty(value = "表明", required = true)
    private String tableName;
}
