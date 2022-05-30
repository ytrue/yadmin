package com.ytrue.yadmin.modules.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.modules.generator.dao.GenTableFieldDao;
import com.ytrue.yadmin.modules.generator.model.GenTableField;
import com.ytrue.yadmin.modules.generator.service.GenTableFieldService;
import org.springframework.stereotype.Service;

/**
 * @author ytrue
 * @date 2022/5/19 16:41
 * @description GenTableFieldServiceImpl
 */
@Service
public class GenTableFieldServiceImpl extends ServiceImpl<GenTableFieldDao, GenTableField> implements GenTableFieldService {
}
