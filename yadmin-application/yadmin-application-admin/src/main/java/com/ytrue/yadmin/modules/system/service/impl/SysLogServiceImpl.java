package com.ytrue.yadmin.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.modules.system.dao.SysLogDAO;
import com.ytrue.yadmin.modules.system.model.SysLog;
import com.ytrue.yadmin.modules.system.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysLogServiceImpl extends ServiceImpl<SysLogDAO, SysLog> implements SysLogService {

    @Autowired
    private SysLogDAO sysLogDAO;


}
