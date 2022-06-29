package com.ytrue.yadmin.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.modules.system.model.SysMenu;
import com.ytrue.yadmin.modules.system.dao.SysMenuDao;
import com.ytrue.yadmin.modules.system.service.SysMenuService;

import org.springframework.stereotype.Service;

/**
* @author ytrue
* @date 2022-06-29
* @description 菜单管理Service实现类
*/
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenu> implements SysMenuService {
}
