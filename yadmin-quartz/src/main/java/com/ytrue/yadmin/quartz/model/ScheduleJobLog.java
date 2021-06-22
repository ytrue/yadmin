package com.ytrue.yadmin.quartz.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description ScheduleJobLog
 */
@Data
@TableName("schedule_job_log")
public class ScheduleJobLog {
    /**
     * 任务日志id
     */
    @TableId

    private Long logId;

    /**
     * 任务id
     */

    private Long jobId;

    /**
     * spring bean名称
     */

    private String beanName;

    /**
     * 方法名
     */

    private String methodName;

    /**
     * 参数
     */
    private String params;

    /**
     * 任务状态    1：成功    0：失败
     */
    private Integer status;

    /**
     * 失败信息
     */
    private String error;

    /**
     * 耗时(单位：毫秒)
     */
    private Integer times;

    /**
     * 创建时间
     */
    private Date createTime;
}
