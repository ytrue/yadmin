package com.ytrue.yadmin.modules.quartz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ytrue.yadmin.modules.quartz.model.ScheduleJob;
import com.ytrue.yadmin.tools.query.entity.QueryEntity;


/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description ScheduleJobService
 */
public interface ScheduleJobService extends IService<ScheduleJob> {


    /**
     * 分页查询
     *
     * @param queryEntity
     * @return
     */
    IPage<ScheduleJob> paginate(QueryEntity<ScheduleJob> queryEntity);

    /**
     * 保存并开始定时任务
     *
     * @param scheduleJob
     */
    void saveAndStart(ScheduleJob scheduleJob);

    /**
     * 更新定时任务
     *
     * @param scheduleJob
     */
    void updateScheduleJob(ScheduleJob scheduleJob);

    /**
     * 批量删除定时任务
     *
     * @param jobIds 需要删除的job id列表
     */
    void deleteBatch(Long[] jobIds);


    /**
     * 批量更新定时任务状态
     *
     * @param jobIds 需要更新的job id列表
     * @param status 更新后的状态
     * @return
     */
    void updateBatch(Long[] jobIds, int status);

    /**
     * 立即执行
     *
     * @param jobIds job id列表
     */
    void run(Long[] jobIds);

    /**
     * 暂停运行
     *
     * @param jobIds job id列表
     */
    void pause(Long[] jobIds);

    /**
     * 恢复运行
     *
     * @param jobIds job id列表
     */
    void resume(Long[] jobIds);


}
