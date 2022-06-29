package com.ytrue.yadmin.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.modules.system.model.SysRole;
import com.ytrue.yadmin.modules.system.dao.SysRoleDao;
import com.ytrue.yadmin.modules.system.service.SysRoleService;

import org.springframework.stereotype.Service;

/**
* @author ytrue
* @date 2022-06-29
* @description 角色Service实现类
*/
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements SysRoleService {
}
