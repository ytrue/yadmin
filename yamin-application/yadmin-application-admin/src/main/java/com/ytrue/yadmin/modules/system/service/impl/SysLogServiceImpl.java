package com.ytrue.yadmin.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ytrue.yadmin.dao.system.SysLogDao;
import com.ytrue.yadmin.model.system.SysLog;
import com.ytrue.yadmin.modules.system.service.SysLogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author lgh
 */
@Service("sysLogService")
@AllArgsConstructor
public class SysLogServiceImpl extends ServiceImpl<SysLogDao, SysLog> implements SysLogService {

}
