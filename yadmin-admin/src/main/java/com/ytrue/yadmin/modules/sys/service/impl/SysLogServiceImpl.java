package com.ytrue.yadmin.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ytrue.yadmin.modules.sys.dao.SysLogMapper;
import com.ytrue.yadmin.modules.sys.model.SysLog;
import com.ytrue.yadmin.modules.sys.service.SysLogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author lgh
 */
@Service("sysLogService")
@AllArgsConstructor
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

    private final SysLogMapper sysLogMapper;
}
