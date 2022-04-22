package com.ytrue.yadmin.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.modules.system.dao.SysQuartzJobDAO;
import com.ytrue.yadmin.modules.system.model.SysQuartzJob;
import com.ytrue.yadmin.modules.system.service.SysQuartzJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysQuartzJobServiceImpl extends ServiceImpl<SysQuartzJobDAO, SysQuartzJob> implements SysQuartzJobService {

    @Autowired
    private SysQuartzJobDAO sysQuartzJobDAO;


}
