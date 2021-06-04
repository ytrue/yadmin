package com.ytrue.yadmin.quartz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ytrue.yadmin.quartz.dao.ScheduleJobLogMapper;
import com.ytrue.yadmin.quartz.model.ScheduleJobLog;
import com.ytrue.yadmin.quartz.service.ScheduleJobLogService;
import org.springframework.stereotype.Service;

/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description ScheduleJobLogServiceImpl
 */
@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl extends ServiceImpl<ScheduleJobLogMapper, ScheduleJobLog> implements ScheduleJobLogService {

}
