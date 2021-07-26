package com.ytrue.yadmin.modules.quartz.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ytrue.yadmin.log.annotation.SysLog;
import com.ytrue.yadmin.exeption.YadminException;
import com.ytrue.yadmin.modules.quartz.model.ScheduleJob;
import com.ytrue.yadmin.modules.quartz.service.ScheduleJobService;
import com.ytrue.yadmin.search.SearchModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 定时任务控制器
 */
@Slf4j
@RestController
@AllArgsConstructor
@Api(tags = "定时任务")
@RequestMapping("/sys/schedule")
public class ScheduleJobController {

    private final ScheduleJobService scheduleJobService;


    @PostMapping("/page")
    @ApiOperation("分页查询数据")
    @PreAuthorize("@pms.hasPermission('sys:schedule:page')")
    public IPage<ScheduleJob> page(@RequestBody SearchModel<ScheduleJob> searchModel) {
        return scheduleJobService.page(searchModel.getPage(), searchModel.getQueryModel());
    }


    @GetMapping("/{jobId}/info")
    @ApiOperation("定时任务信息")
    @PreAuthorize("@pms.hasPermission('sys:schedule:info')")
    public ScheduleJob info(@PathVariable("jobId") Long jobId) {
        return scheduleJobService.getById(jobId);
    }


    @PostMapping
    @SysLog("保存定时任务")
    @ApiOperation("保存定时任务")
    @PreAuthorize("@pms.hasPermission('sys:schedule:save')")
    public void save(@Validated @RequestBody ScheduleJob scheduleJob) {
        int dbAlikeCount = scheduleJobService.count(new LambdaQueryWrapper<ScheduleJob>().eq(ScheduleJob::getBeanName, scheduleJob.getBeanName()).eq(ScheduleJob::getMethodName, scheduleJob.getMethodName()));
        if (dbAlikeCount > 0) {
            throw new YadminException("定时任务已存在");
        }
        scheduleJobService.saveAndStart(scheduleJob);
    }


    @PutMapping
    @SysLog("修改定时任务")
    @ApiOperation("修改定时任务")
    @PreAuthorize("@pms.hasPermission('sys:schedule:update')")
    public void update(@Validated @RequestBody ScheduleJob scheduleJob) {

        int dbAlikeCount = scheduleJobService.count(new LambdaQueryWrapper<ScheduleJob>().eq(ScheduleJob::getBeanName, scheduleJob.getBeanName()).eq(ScheduleJob::getMethodName, scheduleJob.getMethodName()).ne(ScheduleJob::getJobId, scheduleJob.getJobId()));
        if (dbAlikeCount > 0) {
            throw new YadminException("定时任务已存在");
        }
        scheduleJobService.updateScheduleJob(scheduleJob);
    }


    @DeleteMapping
    @SysLog("删除定时任务")
    @ApiOperation("删除定时任务")
    @PreAuthorize("@pms.hasPermission('sys:schedule:delete')")
    public void delete(@RequestBody Long[] jobIds) {
        scheduleJobService.deleteBatch(jobIds);
    }


    @PostMapping("/run")
    @SysLog("立即执行任务")
    @ApiOperation("立即执行任务")
    @PreAuthorize("@pms.hasPermission('sys:schedule:run')")
    public void run(@RequestBody Long[] jobIds) {
        scheduleJobService.run(jobIds);
    }


    @PostMapping("/pause")
    @SysLog("暂停定时任务")
    @ApiOperation("暂停定时任务")
    @PreAuthorize("@pms.hasPermission('sys:schedule:pause')")
    public void pause(@RequestBody Long[] jobIds) {
        scheduleJobService.pause(jobIds);
    }


    @PostMapping("/resume")
    @SysLog("恢复定时任务")
    @ApiOperation("恢复定时任务")
    @PreAuthorize("@pms.hasPermission('sys:schedule:resume')")
    public void resume(@RequestBody Long[] jobIds) {
        scheduleJobService.resume(jobIds);
    }

}
