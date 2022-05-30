package com.ytrue.yadmin.modules.system.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author ytrue
 * @date 2022/4/13 14:25
 * @description 用户与岗位关联
 */
@Data
@TableName("sys_users_jobs")
public class SysUsersJobs {
    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 岗位ID
     */
    @TableId
    @TableField("job_id")
    private Long jobId;
}
