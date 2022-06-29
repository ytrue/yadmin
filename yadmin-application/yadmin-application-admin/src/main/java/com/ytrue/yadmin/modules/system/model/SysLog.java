package com.ytrue.yadmin.modules.system.model;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import java.time.LocalDateTime;

/**
* @author ytrue
* @date 2022-06-29
* @description 操作日志实体类
*/
@Data
@Builder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper=false)
@ApiModel(value = "操作日志")
@TableName("sys_log")
public class SysLog implements Serializable {


    private static final long serialVersionUID = 8776612911433518626L;
    @TableId
    @TableField("log_id")
    @ApiModelProperty(value = "id")
    private Long logId;


    @TableField("request_ip")
    @ApiModelProperty(value = "操作IP")
    private String requestIp;


    @TableField("type")
    @ApiModelProperty(value = "OPT:操作类型;EX:异常类型")
    private String type;


    @TableField("username")
    @ApiModelProperty(value = "操作人")
    private String username;


    @TableField("description")
    @ApiModelProperty(value = "操作描述")
    private String description;


    @TableField("class_path")
    @ApiModelProperty(value = "类路径")
    private String classPath;


    @TableField("action_method")
    @ApiModelProperty(value = "请求方法")
    private String actionMethod;


    @TableField("request_uri")
    @ApiModelProperty(value = "请求url")
    private String requestUri;


    @TableField("http_method")
    @ApiModelProperty(value = "请求参数")
    private String httpMethod;


    @TableField("params")
    @ApiModelProperty(value = "返回值,项目使用了注解返回包装，拿不到值，所先注释")
    private String params;


    @TableField("ex_desc")
    @ApiModelProperty(value = "异常详情信息")
    private String exDesc;


    @TableField("ex_detail")
    @ApiModelProperty(value = "异常描述")
    private Object exDetail;


    @TableField("start_time")
    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startTime;


    @TableField("finish_time")
    @ApiModelProperty(value = "完成时间")
    private LocalDateTime finishTime;


    @TableField("consuming_time")
    @ApiModelProperty(value = "消耗时间")
    private Long consumingTime;


    @TableField("ua")
    @ApiModelProperty(value = "浏览器")
    private String ua;


    @TableField("user_id")
    @ApiModelProperty(value = "操作人id")
    private Long userId;

}
