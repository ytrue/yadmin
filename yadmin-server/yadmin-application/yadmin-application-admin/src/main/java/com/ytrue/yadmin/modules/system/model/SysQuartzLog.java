package com.ytrue.yadmin.modules.system.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


/**
 * @author ytrue
 * @date 2022/4/13 14:25
 * @description 定时任务日志
 */
@Data
@TableName("sys_quartz_log")
public class SysQuartzLog {
    /**
     * ID
     */
    @TableId
    @TableField("log_id")
    private Long logId;
    /**
     *
     */
    @TableField("bean_name")
    private String beanName;
    /**
     *
     */
    @TableField("create_time")
    private Date createTime;
    /**
     *
     */
    @TableField("cron_expression")
    private String cronExpression;
    /**
     *
     */
    @TableField("exception_detail")
    private String exceptionDetail;
    /**
     *
     */
    @TableField("is_success")
    private boolean isSuccess;
    /**
     *
     */
    @TableField("job_name")
    private String jobName;
    /**
     *
     */
    @TableField("method_name")
    private String methodName;
    /**
     *
     */
    @TableField("params")
    private String params;
    /**
     *
     */
    @TableField("time")
    private Long time;
}
