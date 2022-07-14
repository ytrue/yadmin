package com.ytrue.yadmin.modules.quartz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ytrue.yadmin.modules.generator.model.GenBaseClass;
import com.ytrue.yadmin.modules.quartz.model.ScheduleJobLog;
import com.ytrue.yadmin.tools.query.entity.QueryEntity;


/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description ScheduleJobLogService
 */
public interface ScheduleJobLogService extends IService<ScheduleJobLog> {

    /**
     * 分页查询
     *
     * @param queryEntity
     * @return
     */
    IPage<ScheduleJobLog> paginate(QueryEntity<ScheduleJobLog> queryEntity);
}
