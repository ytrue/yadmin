package com.ytrue.yadmin.modules.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ytrue.yadmin.modules.system.model.SysLog;
import com.ytrue.yadmin.search.SearchModel;

import com.ytrue.yadmin.modules.system.service.SysLogService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 系统日志
 */

@AllArgsConstructor
@RestController
@RequestMapping("sys/log")
public class SysLogController {

    private final SysLogService sysLogService;

    /**
     * 列表
     * <p>
     * hasAuthority
     *
     * @param sysLogSearchModel
     * @return
     */
    @PostMapping("page")
    @PreAuthorize("@pms.hasPermission('sys:log:page')")
    public IPage<SysLog> page(@RequestBody SearchModel<SysLog> sysLogSearchModel) {
        String username = ((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return sysLogService.page(
                sysLogSearchModel.getPage(),
                sysLogSearchModel.getQueryModel().lambda().orderByDesc(SysLog::getUserId).eq(SysLog::getUsername, username)
        );
    }
}
