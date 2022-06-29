package com.ytrue.yadmin.modules.generator.model;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


/**
 * @author ytrue
 * @date 2022/6/13 17:59
 * @description 表信息
 */

@Data
@ApiModel("表信息")
@TableName("gen_table_info")
@Accessors(chain = true)
public class GenTableInfo  implements Serializable {

    private static final long serialVersionUID = 5844494419588333242L;
    @TableId
    @TableField("id")
    @ApiModelProperty(value = "id", required = true)
    private Long id;

    @TableField("table_name")
    @ApiModelProperty(value = "表名")
    private String tableName;

    @TableField("class_name")
    @ApiModelProperty(value = "实体类名称")
    private String className;

    @TableField("table_comment")
    @ApiModelProperty(value = "功能名")
    private String tableComment;

    @TableField("package_name")
    @ApiModelProperty(value = "项目包名")
    private String packageName;

    @TableField("version")
    @ApiModelProperty(value = "项目版本号")
    private String version;

    @TableField("author")
    @ApiModelProperty(value = "作者")
    private String author;

    @TableField("email")
    @ApiModelProperty(value = "邮箱")
    private String email;

    @TableField("backend_path")
    @ApiModelProperty(value = "后端生成路径")
    private String backendPath;

    @TableField("frontend_path")
    @ApiModelProperty(value = "前端生成路径")
    private String frontendPath;

    @TableField("module_name")
    @ApiModelProperty(value = "模块名")
    private String moduleName;

    @TableField("sub_module_name")
    @ApiModelProperty(value = "子模块名")
    private String subModuleName;

    @TableField("datasource_id")
    @ApiModelProperty(value = "数据源ID")
    private Long datasourceId;


    @TableField(value = "baseclass_id", updateStrategy = FieldStrategy.IGNORED)
    @ApiModelProperty(value = "基类ID")
    private Long baseclassId;


    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
}
