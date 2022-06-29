package com.ytrue.yadmin.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.modules.system.model.SysUserRole;
import com.ytrue.yadmin.modules.system.dao.SysUserRoleDao;
import com.ytrue.yadmin.modules.system.service.SysUserRoleService;

import org.springframework.stereotype.Service;

/**
* @author ytrue
* @date 2022-06-29
* @description 用户与角色对应关系Service实现类
*/
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRole> implements SysUserRoleService {
}
