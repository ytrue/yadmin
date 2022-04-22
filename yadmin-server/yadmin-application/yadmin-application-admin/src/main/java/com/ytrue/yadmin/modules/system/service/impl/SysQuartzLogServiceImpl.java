package com.ytrue.yadmin.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.modules.system.dao.SysQuartzLogDAO;
import com.ytrue.yadmin.modules.system.model.SysQuartzLog;
import com.ytrue.yadmin.modules.system.service.SysQuartzLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysQuartzLogServiceImpl extends ServiceImpl<SysQuartzLogDAO, SysQuartzLog> implements SysQuartzLogService {

    @Autowired
    private SysQuartzLogDAO sysQuartzLogDAO;


}
