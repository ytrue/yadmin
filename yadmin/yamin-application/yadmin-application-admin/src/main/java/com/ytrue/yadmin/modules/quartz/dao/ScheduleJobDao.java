package com.ytrue.yadmin.modules.quartz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ytrue.yadmin.modules.quartz.model.ScheduleJob;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;


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
     * <p>
     *
     * update schedule_job set status = #{status} where job_id in
     * <foreach item="jobId" collection="jobIds"  open="(" separator="," close=")">
     * #{jobId}
     * </foreach>
     */
    @Update("<script>" + "update schedule_job set status = #{status} where job_id in<foreach item=\"jobId\" collection=\"jobIds\"  open=\"(\" separator=\",\" close=\")\">#{jobId}</foreach>" + "</script>")
    int updateBatch(@Param("jobIds") Long[] jobIds, @Param("status") int status);
}
