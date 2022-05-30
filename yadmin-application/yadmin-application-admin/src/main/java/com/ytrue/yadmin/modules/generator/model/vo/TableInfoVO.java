package com.ytrue.yadmin.modules.generator.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ytrue.yadmin.core.annotation.ApiModelEnumProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author ytrue
 * @date 2022/5/28 14:21
 * @description TableInfoVO
 */
@Data
@ApiModel("测试实体哈哈哈")
public class TableInfoVO {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "表名")
    private String tableName;

    @ApiModelProperty(value = "实体类名称")
    private String className;

    @ApiModelProperty(value = "数据源ID")
    private Long datasourceId;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;


    @ApiModelEnumProperty(value = "test", enumClass = TypeEnum.class)
    private String type;

    @ApiModelEnumProperty(value = "test1")
    private TypeEnum type1;
}
