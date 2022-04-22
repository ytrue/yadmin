package com.ytrue.yadmin.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.modules.system.dao.SysRoleDAO;
import com.ytrue.yadmin.modules.system.model.SysRole;
import com.ytrue.yadmin.modules.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDAO, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleDAO sysRoleDAO;


}
