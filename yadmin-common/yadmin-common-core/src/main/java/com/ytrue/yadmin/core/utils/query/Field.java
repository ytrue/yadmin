package com.ytrue.yadmin.core.utils.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ytrue
 * @date 2022/4/20 16:06
 * @description 字段
 */
@Data
class Field {
    /**
     * 字段
     */
    @ApiModelProperty(value = "字段")
    private String column;

    /**
     * 内容 ,一般是 string和int
     */
    @ApiModelProperty(value = "内容")
    private Object value;

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    private QueryMethod type;
}
