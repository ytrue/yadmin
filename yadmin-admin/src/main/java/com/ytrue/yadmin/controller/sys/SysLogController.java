package com.ytrue.yadmin.controller.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ytrue.yadmin.common.annotation.WrapResp;
import com.ytrue.yadmin.common.search.SearchModel;
import com.ytrue.yadmin.sys.model.SysLog;
import com.ytrue.yadmin.sys.service.SysLogService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 系统日志
 */
@RestController
@RequestMapping("/sys/log")
@WrapResp
@AllArgsConstructor
public class SysLogController {

    private final SysLogService sysLogService;

    /**
     * 列表
     *
     * hasAuthority
     * @param sysLogSearchModel
     * @return
     */
    @PostMapping("/page")
    @PreAuthorize("@pms.hasPermission('sys:log:page')")
    public IPage<SysLog> page(@RequestBody SearchModel<SysLog> sysLogSearchModel) {
        return sysLogService.page(
                sysLogSearchModel.getPage(),
                sysLogSearchModel.getQueryModel().orderByDesc("id")
        );
    }
}
