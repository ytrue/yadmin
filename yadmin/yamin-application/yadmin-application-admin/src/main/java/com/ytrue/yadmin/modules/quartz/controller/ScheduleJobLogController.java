package com.ytrue.yadmin.modules.quartz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ytrue.yadmin.search.SearchModel;
import com.ytrue.yadmin.modules.quartz.model.ScheduleJobLog;
import com.ytrue.yadmin.modules.quartz.service.ScheduleJobLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
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
@RestController
@AllArgsConstructor
@Api(tags = "定时任务日志")
@RequestMapping("/sys/scheduleLog")
public class ScheduleJobLogController {

    private final ScheduleJobLogService scheduleJobLogService;

    /**
     * 定时日志列表
     *
     * @param searchModel
     * @return
     */
    @PostMapping("/page")
    @ApiOperation("分页查询数据")
    @PreAuthorize("@pms.hasPermission('sys:schedule:log')")
    public IPage<ScheduleJobLog> page(@RequestBody SearchModel<ScheduleJobLog> searchModel) {
        return scheduleJobLogService.page(searchModel.getPage(), searchModel.getQueryModel());
    }
}