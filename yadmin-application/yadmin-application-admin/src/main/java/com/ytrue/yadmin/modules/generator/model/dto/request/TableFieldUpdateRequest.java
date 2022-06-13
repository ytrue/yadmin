package com.ytrue.yadmin.modules.generator.model.dto.request;

import com.ytrue.yadmin.modules.generator.model.GenTableField;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author ytrue
 * @date 2022/6/13 16:46
 * @description TableFieldUpdateRequest
 */
public class TableFieldUpdateRequest {

    @ApiModelProperty(value = "表ID")
    private Long tableId;

    @ApiModelProperty(value = "表字段集合")
    List<GenTableField> tableFields;
}
