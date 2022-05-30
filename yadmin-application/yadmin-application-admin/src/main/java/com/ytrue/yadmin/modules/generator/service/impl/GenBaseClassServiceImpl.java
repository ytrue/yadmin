package com.ytrue.yadmin.modules.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.modules.generator.dao.GenBaseClassDao;
import com.ytrue.yadmin.modules.generator.model.GenBaseClass;
import com.ytrue.yadmin.modules.generator.service.GenBaseClassService;
import org.springframework.stereotype.Service;

/**
 * @author ytrue
 * @date 2022/5/19 16:40
 * @description GenBaseClassServiceImpl
 */
@Service
public class GenBaseClassServiceImpl extends ServiceImpl<GenBaseClassDao, GenBaseClass> implements GenBaseClassService {
}
