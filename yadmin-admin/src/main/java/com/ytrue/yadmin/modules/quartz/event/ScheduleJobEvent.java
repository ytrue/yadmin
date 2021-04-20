package com.ytrue.yadmin.modules.quartz.event;


import com.ytrue.yadmin.modules.quartz.model.ScheduleJob;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 定时任务事件
 */
@Getter
@AllArgsConstructor
public class ScheduleJobEvent {

    private final ScheduleJob scheduleJob;
}
