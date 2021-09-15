package com.ytrue.yadmin.search;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ytrue
 * @date 2021/4/20 13:34
 * @description 字段
 */
@Data
public class Field {

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
