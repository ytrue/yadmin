package com.ytrue.yadmin.modules.system.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


/**
 * @author ytrue
 * @date 2022/4/13 14:25
 * @description 定时任务
 */
@Data
@TableName("sys_quartz_job")
public class SysQuartzJob {
    /**
     * ID
     */
    @TableId
    @TableField("job_id")
    private Long jobId;
    /**
     * Spring Bean名称
     */
    @TableField("bean_name")
    private String beanName;
    /**
     * cron 表达式
     */
    @TableField("cron_expression")
    private String cronExpression;
    /**
     * 状态：1暂停、0启用
     */
    @TableField("is_pause")
    private boolean isPause;
    /**
     * 任务名称
     */
    @TableField("job_name")
    private String jobName;
    /**
     * 方法名称
     */
    @TableField("method_name")
    private String methodName;
    /**
     * 参数
     */
    @TableField("params")
    private String params;
    /**
     * 备注
     */
    @TableField("description")
    private String description;
    /**
     * 负责人
     */
    @TableField("person_in_charge")
    private String personInCharge;
    /**
     * 报警邮箱
     */
    @TableField("email")
    private String email;
    /**
     * 子任务ID
     */
    @TableField("sub_task")
    private String subTask;
    /**
     * 任务失败后是否暂停
     */
    @TableField("pause_after_failure")
    private boolean pauseAfterFailure;
    /**
     * 创建者
     */
    @TableField("create_by")
    private String createBy;
    /**
     * 更新者
     */
    @TableField("update_by")
    private String updateBy;
    /**
     * 创建日期
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
}
