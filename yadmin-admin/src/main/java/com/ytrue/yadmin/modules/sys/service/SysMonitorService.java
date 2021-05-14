package com.ytrue.yadmin.modules.sys.service;

import java.util.Map;

/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description SysMonitorService
 */
public interface SysMonitorService {
    /**
     * 查询数据分页
     *
     * @return Map<String, Object>
     */
    Map<String, Object> getServers();
}
