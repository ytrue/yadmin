package com.ytrue.yadmin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.model.SysUser;
import com.ytrue.yadmin.dao.SysUserDao;
import com.ytrue.yadmin.service.SysUserService;

import org.springframework.stereotype.Service;

/**
* @author ytrue
* @date 2022-06-20
* @description 系统用户Service实现类
*/
@Service
public class GenBaseClassServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {
}
