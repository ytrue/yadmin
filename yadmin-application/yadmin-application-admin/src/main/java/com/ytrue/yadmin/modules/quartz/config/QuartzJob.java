package com.ytrue.yadmin.modules.quartz.config;


import com.ytrue.yadmin.modules.quartz.event.ScheduleJobEvent;
import com.ytrue.yadmin.modules.quartz.model.ScheduleJob;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 该类将会被org.springframework.scheduling.quartz.SpringBeanJobFactory 实例化并使@Autowired 生效
 */
@Slf4j
@DisallowConcurrentExecution
public class QuartzJob implements Job {


    @Autowired
    private ApplicationEventPublisher publisher;

    /**
     * 任务调度参数key
     */
    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";

    @Override
    @SneakyThrows
    public void execute(JobExecutionContext jobExecutionContext) {
        ScheduleJob sysJob = (ScheduleJob) jobExecutionContext.getMergedJobDataMap().get(JOB_PARAM_KEY);
        publisher.publishEvent(new ScheduleJobEvent(sysJob));
    }

}
