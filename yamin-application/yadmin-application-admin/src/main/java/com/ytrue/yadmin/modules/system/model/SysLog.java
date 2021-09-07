package com.ytrue.yadmin.modules.system.model;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ytrue
 * @date 2021/7/6 16:05
 * @description Optlogdto  tmp
 */

@ApiModel(value = "操作日志")
@Data
@TableName("optlogdto")
public class SysLog implements Serializable {
    private static final long serialVersionUID = 6701724385116244971L;
    /**
     * id
     */
    @TableId
    @TableField("log_id")
    @ApiModelProperty(value = "主键")
    private Integer logId;
    /**
     * 操作IP
     */
    @TableField("request_ip")
    @ApiModelProperty(value = "操作IP")
    private String requestIp;
    /**
     * OPT:操作类型;EX:异常类型
     */
    @ApiModelProperty(value = "OPT:操作类型;EX:异常类型")
    @TableField("type")
    private String type;
    /**
     * 操作人
     */
    @ApiModelProperty(value = "操作人")
    @TableField("username")
    private String username;
    /**
     * 操作描述
     */
    @ApiModelProperty(value = "操作描述")
    @TableField("description")
    private String description;
    /**
     * 类路径
     */
    @ApiModelProperty(value = "类路径")
    @TableField("class_path")
    private String classPath;
    /**
     * 请求地址
     */
    @ApiModelProperty(value = "方法名")
    @TableField("action_method")
    private String actionMethod;
    /**
     * 请求类型
     */
    @ApiModelProperty(value = "请求url")
    @TableField("request_uri")
    private String requestUri;
    /**
     * 请求参数
     */
    @ApiModelProperty(value = "请求方法")
    @TableField("http_method")
    private String httpMethod;
    /**
     * 返回值,项目使用了注解返回包装，拿不到值，所先注释
     */
    // @ApiModelProperty(value = "返回值,项目使用了注解返回包装，拿不到值，所先注释")
    @TableField("params")
    @ApiModelProperty(value = "请求参数")
    private String params;
    /**
     * 异常详情信息
     */
    @ApiModelProperty(value = "异常描述")
    @TableField("ex_desc")
    private String exDesc;
    /**
     * 异常描述
     */

    @ApiModelProperty(value = "异常详情信息")
    @TableField("ex_detail")
    private String exDetail;
    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    @TableField("start_time")
    private LocalDateTime startTime;
    /**
     * 完成时间
     */
    @ApiModelProperty(value = "完成时间")
    @TableField("finish_time")
    private LocalDateTime finishTime;
    /**
     * 消耗时间
     */
    @ApiModelProperty(value = "消耗时间")
    @TableField("consuming_time")
    private Integer consumingTime;
    /**
     * 浏览器
     */
    @ApiModelProperty(value = "浏览器")
    @TableField("ua")
    private String ua;
    /**
     * 操作人id
     */
    @ApiModelProperty(value = "操作人id")
    @TableField("user_id")
    private Long userId;
}
