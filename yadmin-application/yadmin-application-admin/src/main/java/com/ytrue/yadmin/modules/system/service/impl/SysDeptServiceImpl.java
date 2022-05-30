package com.ytrue.yadmin.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.modules.system.dao.SysDeptDAO;
import com.ytrue.yadmin.modules.system.model.SysDept;
import com.ytrue.yadmin.modules.system.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysDeptServiceImpl extends ServiceImpl<SysDeptDAO, SysDept> implements SysDeptService {

    @Autowired
    private SysDeptDAO sysDeptDAO;


}
