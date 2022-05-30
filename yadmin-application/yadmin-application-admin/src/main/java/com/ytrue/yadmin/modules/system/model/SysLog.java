package com.ytrue.yadmin.modules.system.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;


/**
 * @author ytrue
 * @date 2022/4/13 14:25
 * @description 系统日志
 */
@Data
@TableName("sys_log")
@Builder
@ApiModel(value = "系统日志")
@Accessors(chain = true)
public class SysLog {

    @TableId
    @TableField("log_id")
    @ApiModelProperty(value = "ID")
    private Long logId;

    @TableField("description")
    @ApiModelProperty(value = "描述")
    private String description;

    @TableField("log_type")
    @ApiModelProperty(value = "类型")
    private String logType;

    @TableField("method")
    @ApiModelProperty(value = "方法")
    private String method;

    @TableField("params")
    @ApiModelProperty(value = "参数")
    private String params;

    @TableField("request_ip")
    @ApiModelProperty(value = "请求IP")
    private String requestIp;

    @TableField("time")
    @ApiModelProperty(value = "时间")
    private Long time;

    @TableField("username")
    @ApiModelProperty(value = "用户名")
    private String username;

    @TableField("address")
    @ApiModelProperty(value = "地址")
    private String address;

    @TableField("browser")
    @ApiModelProperty(value = "浏览器")
    private String browser;

    @TableField("exception_detail")
    @ApiModelProperty(value = "异常详情")
    private String exceptionDetail;

    @TableField("create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
