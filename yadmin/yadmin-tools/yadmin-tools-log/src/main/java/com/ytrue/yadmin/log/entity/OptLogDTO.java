package com.ytrue.yadmin.log.entity;


import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author ytrue
 * @date 2021/7/1 13:39
 * @description 实体类 系统日志
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode
@Accessors(chain = true)
public class OptLogDTO {

    private static final long serialVersionUID = 1L;

    /**
     * 操作IP
     */
    private String requestIp;

    /**
     * 日志类型
     * #LogType{OPT:操作类型;EX:异常类型}
     */
    private String type;

    /**
     * 操作人
     */
    private String username;

    /**
     * 操作描述
     */
    private String description;

    /**
     * 类路径
     */
    private String classPath;

    /**
     * 请求类型
     */
    private String actionMethod;

    /**
     * 请求地址
     */
    private String requestUri;

    /**
     * 请求类型
     * #HttpMethod{GET:GET请求;POST:POST请求;PUT:PUT请求;DELETE:DELETE请求;PATCH:PATCH请求;TRACE:TRACE请求;HEAD:HEAD请求;OPTIONS:OPTIONS请求;}
     */
    private String httpMethod;

    /**
     * 请求参数
     */
    private String params;

    /**
     * 返回值,项目使用了注解返回包装，拿不到值，所先注释
     */
    //private String result;

    /**
     * 异常详情信息
     */
    private String exDesc;

    /**
     * 异常描述
     */
    private String exDetail;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 完成时间
     */
    private LocalDateTime finishTime;

    /**
     * 消耗时间
     */
    private Long consumingTime;

    /**
     * 浏览器
     */
    private String ua;

}
