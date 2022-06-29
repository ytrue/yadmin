package com.ytrue.yadmin.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.modules.system.model.SysRoleMenu;
import com.ytrue.yadmin.modules.system.dao.SysRoleMenuDao;
import com.ytrue.yadmin.modules.system.service.SysRoleMenuService;

import org.springframework.stereotype.Service;

/**
* @author ytrue
* @date 2022-06-29
* @description 角色与菜单对应关系Service实现类
*/
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuDao, SysRoleMenu> implements SysRoleMenuService {
}
