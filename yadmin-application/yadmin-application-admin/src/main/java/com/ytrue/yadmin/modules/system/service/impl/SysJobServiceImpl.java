package com.ytrue.yadmin.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.modules.system.dao.SysJobDAO;
import com.ytrue.yadmin.modules.system.model.SysJob;
import com.ytrue.yadmin.modules.system.service.SysJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysJobServiceImpl extends ServiceImpl<SysJobDAO, SysJob> implements SysJobService {

    @Autowired
    private SysJobDAO sysJobDAO;


}
