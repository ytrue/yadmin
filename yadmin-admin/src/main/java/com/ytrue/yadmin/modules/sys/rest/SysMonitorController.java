package com.ytrue.yadmin.modules.sys.rest;


import com.ytrue.yadmin.common.annotation.WrapResp;
import com.ytrue.yadmin.modules.sys.service.SysMonitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 服务监控管理
 */
@WrapResp
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys/monitor")
public class SysMonitorController {

    private final SysMonitorService serverService;

    @GetMapping
    //@PreAuthorize("@pms.hasPermission('sys:monito:page' )")
    public Map<String, Object> query() {
        return serverService.getServers();
    }
}
