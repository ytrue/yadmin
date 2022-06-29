package com.ytrue.yadmin.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.modules.system.model.SysUser;
import com.ytrue.yadmin.modules.system.dao.SysUserDao;
import com.ytrue.yadmin.modules.system.service.SysUserService;

import org.springframework.stereotype.Service;

/**
* @author ytrue
* @date 2022-06-29
* @description 系统用户Service实现类
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {
}
