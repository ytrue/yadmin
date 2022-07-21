package com.ytrue.yadmin.modules.quartz.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.modules.quartz.dao.ScheduleJobDao;
import com.ytrue.yadmin.modules.quartz.enums.ScheduleStatus;
import com.ytrue.yadmin.modules.quartz.model.ScheduleJob;
import com.ytrue.yadmin.modules.quartz.service.ScheduleJobService;
import com.ytrue.yadmin.modules.quartz.service.manager.ScheduleManager;
import com.ytrue.yadmin.tools.query.entity.QueryEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronTrigger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description ScheduleJobServiceImpl
 */
@Service
@Slf4j
@AllArgsConstructor
public class ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobDao, ScheduleJob> implements ScheduleJobService, InitializingBean {


    private final ScheduleJobDao scheduleJobDao;

    private final ScheduleManager scheduleManager;


    /**
     * 初始化bean时，初始化定时器
     */
    @Override
    public void afterPropertiesSet() {
        list().forEach(scheduleJob -> {
            CronTrigger trigger = scheduleManager.getCronTrigger(scheduleJob);
            // 如果定时任务不存在，则创建定时任务
            if (trigger == null) {
                scheduleManager.createScheduleJob(scheduleJob);
            } else if (ScheduleStatus.NORMAL.getType().equals(scheduleJob.getStatus())) {
                scheduleManager.resumeJob(scheduleJob);
            } else if (ScheduleStatus.PAUSE.getType().equals(scheduleJob.getStatus())) {
                scheduleManager.pauseJob(scheduleJob);
            }
        });
    }

    @Override
    public IPage<ScheduleJob> paginate(QueryEntity<ScheduleJob> queryEntity) {
        queryEntity = Objects.isNull(queryEntity) ? new QueryEntity<>() : queryEntity;
        return page(queryEntity.getPage(), queryEntity.getQueryModel());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveAndStart(ScheduleJob scheduleJob) {
        scheduleJob.setCreateTime(DateUtil.date());
        scheduleJob.setStatus(ScheduleStatus.NORMAL.getType());
        scheduleJobDao.insert(scheduleJob);

        scheduleManager.createScheduleJob(scheduleJob);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateScheduleJob(ScheduleJob scheduleJob) {
        scheduleManager.updateScheduleJob(scheduleJob);
        scheduleJobDao.updateById(scheduleJob);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] jobIds) {
        List<Long> ids = Arrays.asList(jobIds);
        this.listByIds(ids).forEach(scheduleManager::deleteScheduleJob);
        scheduleJobDao.deleteBatchIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBatch(Long[] jobIds, int status) {
        Arrays.asList(jobIds).forEach(jobId -> scheduleJobDao.updateById(new ScheduleJob().setStatus(status).setId(jobId)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void run(Long[] jobIds) {
        for (Long jobId : jobIds) {
            scheduleManager.run(scheduleJobDao.selectById(jobId));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void pause(Long[] jobIds) {
        this.listByIds(Arrays.asList(jobIds)).forEach(scheduleManager::pauseJob);
        updateBatch(jobIds, ScheduleStatus.PAUSE.getType());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resume(Long[] jobIds) {
        this.listByIds(Arrays.asList(jobIds)).forEach(scheduleManager::resumeJob);

        updateBatch(jobIds, ScheduleStatus.NORMAL.getType());
    }


}
