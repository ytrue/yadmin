package com.ytrue.yadmin.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ytrue.yadmin.sys.dao.SysLogDao;
import com.ytrue.yadmin.sys.model.SysLog;
import com.ytrue.yadmin.sys.service.SysLogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author lgh
 */
@Service("sysLogService")
@AllArgsConstructor
public class SysLogServiceImpl extends ServiceImpl<SysLogDao, SysLog> implements SysLogService {

    private final SysLogDao sysLogMapper;
}
