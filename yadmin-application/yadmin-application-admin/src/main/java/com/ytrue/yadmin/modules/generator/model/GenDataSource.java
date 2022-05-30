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
 * @description 数据源管理
 */
@Data
@TableName("gen_datasource")
@ApiModel(value = "数据源管理")
@Accessors(chain = true)
public class GenDataSource implements Serializable {

    private static final long serialVersionUID = 8599780725997944971L;
    @TableId
    @TableField("id")
    @ApiModelProperty(value = "id", required = true)
    private Long id;

    @TableField("db_type")
    @ApiModelProperty(value = "数据库类型 MySQL,Oracle,PostgreSQL")
    private String dbType;

    @TableField("conn_name")
    @ApiModelProperty(value = "连接名")
    private String connName;

    @TableField("conn_url")
    @ApiModelProperty(value = "URL")
    private String connUrl;

    @TableField("username")
    @ApiModelProperty(value = "用户名")
    private String username;

    @TableField("password")
    @ApiModelProperty(value = "密码")
    private String password;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
}
