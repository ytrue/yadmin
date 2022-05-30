package com.ytrue.yadmin.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.modules.system.dao.SysUserDAO;
import com.ytrue.yadmin.modules.system.model.SysUser;
import com.ytrue.yadmin.modules.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl extends ServiceImpl<SysUserDAO, SysUser> implements SysUserService {

    @Autowired
    private SysUserDAO sysUserDAO;


}
