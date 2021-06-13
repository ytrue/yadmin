package com.ytrue.yadmin.quartz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ytrue.yadmin.quartz.model.ScheduleJob;
import org.apache.ibatis.annotations.Param;


/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 任务调度mapper
 */
public interface ScheduleJobDao extends BaseMapper<ScheduleJob> {

    /**
     * 批量修改任务状态
     *
     * @param jobIds 调度任务id
     * @param status 任务状态
     * @return 修改成功条数
     */
    int updateBatch(@Param("jobIds") Long[] jobIds, @Param("status") int status);
}
