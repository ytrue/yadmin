package com.ytrue.yadmin.quartz.rest;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ytrue.yadmin.quartz.model.ScheduleJob;
import com.ytrue.yadmin.quartz.service.ScheduleJobService;
import com.ytrue.yadmin.common.annotation.AutoValid;
import com.ytrue.yadmin.common.annotation.WrapResp;
import com.ytrue.yadmin.common.exeption.YadminException;
import com.ytrue.yadmin.common.search.SearchModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 定时任务控制器
 */
@Slf4j
@WrapResp
@RestController
@AllArgsConstructor
@RequestMapping("/sys/schedule")
public class ScheduleJobController {

    private final ScheduleJobService scheduleJobService;

    /**
     * 定时任务列表
     *
     * @param searchModel
     * @return
     */
    @PostMapping("/page")
    @PreAuthorize("@pms.hasPermission('sys:schedule:page')")
    public IPage<ScheduleJob> page(@RequestBody SearchModel<ScheduleJob> searchModel) {
        return scheduleJobService.page(searchModel.getPage(), searchModel.getQueryModel());
    }

    /**
     * 定时任务信息
     *
     * @param jobId
     * @return
     */
    @GetMapping("/info/{jobId}")
    @PreAuthorize("@pms.hasPermission('sys:schedule:info')")
    public ScheduleJob info(@PathVariable("jobId") Long jobId) {
        return scheduleJobService.getById(jobId);
    }

    /**
     * 保存定时任务
     *
     * @param scheduleJob
     */
    //@SysLog("保存定时任务")
    @PostMapping
    @AutoValid(entity = ScheduleJob.class)
    @PreAuthorize("@pms.hasPermission('sys:schedule:save')")
    public void save(@RequestBody ScheduleJob scheduleJob) {
        int dbAlikeCount = scheduleJobService.count(new LambdaQueryWrapper<ScheduleJob>().eq(ScheduleJob::getBeanName, scheduleJob.getBeanName()).eq(ScheduleJob::getMethodName, scheduleJob.getMethodName()));
        if (dbAlikeCount > 0) {
            throw new YadminException("定时任务已存在");
        }
        scheduleJobService.saveAndStart(scheduleJob);
    }

    /**
     * 修改定时任务
     *
     * @param scheduleJob
     */
   // @SysLog("修改定时任务")
    @PutMapping
    @AutoValid(entity = ScheduleJob.class)
    @PreAuthorize("@pms.hasPermission('sys:schedule:update')")
    public void update(@RequestBody ScheduleJob scheduleJob) {

        int dbAlikeCount = scheduleJobService.count(new LambdaQueryWrapper<ScheduleJob>().eq(ScheduleJob::getBeanName, scheduleJob.getBeanName()).eq(ScheduleJob::getMethodName, scheduleJob.getMethodName()).ne(ScheduleJob::getJobId, scheduleJob.getJobId()));
        if (dbAlikeCount > 0) {
            throw new YadminException("定时任务已存在");
        }
        scheduleJobService.updateScheduleJob(scheduleJob);
    }

    /**
     * 删除定时任务
     *
     * @param jobIds
     */
   // @SysLog("删除定时任务")
    @DeleteMapping
    @PreAuthorize("@pms.hasPermission('sys:schedule:delete')")
    public void delete(@RequestBody Long[] jobIds) {
        scheduleJobService.deleteBatch(jobIds);
    }

    /**
     * 立即执行任务
     *
     * @param jobIds
     */
   // @SysLog("立即执行任务")
    @PostMapping("/run")
    @PreAuthorize("@pms.hasPermission('sys:schedule:run')")
    public void run(@RequestBody Long[] jobIds) {
        scheduleJobService.run(jobIds);
    }

    /**
     * 暂停定时任务
     *
     * @param jobIds
     */
   // @SysLog("暂停定时任务")
    @PostMapping("/pause")
    @PreAuthorize("@pms.hasPermission('sys:schedule:pause')")
    public void pause(@RequestBody Long[] jobIds) {
        scheduleJobService.pause(jobIds);
    }

    /**
     * 恢复定时任务
     *
     * @param jobIds
     */
    //@SysLog("恢复定时任务")
    @PostMapping("/resume")
    @PreAuthorize("@pms.hasPermission('sys:schedule:resume')")
    public void resume(@RequestBody Long[] jobIds) {
        scheduleJobService.resume(jobIds);
    }

}
