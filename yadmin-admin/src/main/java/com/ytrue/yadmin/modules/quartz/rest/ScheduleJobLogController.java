package com.ytrue.yadmin.modules.quartz.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ytrue.yadmin.common.annotation.WrapResp;
import com.ytrue.yadmin.common.search.SearchModel;
import com.ytrue.yadmin.modules.quartz.model.ScheduleJobLog;
import com.ytrue.yadmin.modules.quartz.service.ScheduleJobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 定时任务日志控制器
 */
@WrapResp
@RestController
@RequestMapping("/sys/scheduleLog")
public class ScheduleJobLogController {

    @Autowired
    private ScheduleJobLogService scheduleJobLogService;

    /**
     * 定时任务日志列表
     */
    @PostMapping("/page")
    @PreAuthorize("@pms.hasPermission('sys:schedule:log')")
    public IPage<ScheduleJobLog> page(@RequestBody SearchModel<ScheduleJobLog> searchModel) {
        return scheduleJobLogService.page(searchModel.getPage(), searchModel.getQueryModel());
    }
}
