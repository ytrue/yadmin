package com.ytrue.yadmin.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.modules.system.dao.SysMenuDAO;
import com.ytrue.yadmin.modules.system.model.SysMenu;
import com.ytrue.yadmin.modules.system.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDAO, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuDAO sysMenuDAO;


}
