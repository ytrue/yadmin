package com.ytrue.yadmin.modules.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.modules.generator.dao.GenFieldTypeDao;
import com.ytrue.yadmin.modules.generator.model.GenFieldType;
import com.ytrue.yadmin.modules.generator.service.GenFieldTypeService;
import org.springframework.stereotype.Service;

/**
 * @author ytrue
 * @date 2022/5/19 16:41
 * @description GenFieldTypeServiceImpl
 */
@Service
public class GenFieldTypeServiceImpl extends ServiceImpl<GenFieldTypeDao, GenFieldType> implements GenFieldTypeService {
}
