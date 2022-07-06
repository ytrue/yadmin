package com.ytrue.yadmin.modules.generator.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.tools.query.entity.QueryEntity;
import com.ytrue.yadmin.modules.generator.dao.GenBaseClassDao;
import com.ytrue.yadmin.modules.generator.model.GenBaseClass;
import com.ytrue.yadmin.modules.generator.service.GenBaseClassService;
import com.ytrue.yadmin.modules.system.model.SysLog;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author ytrue
 * @date 2022/5/19 16:40
 * @description GenBaseClassServiceImpl
 */
@Service
public class GenBaseClassServiceImpl extends ServiceImpl<GenBaseClassDao, GenBaseClass> implements GenBaseClassService {

    @Override
    public IPage<GenBaseClass> paginate(QueryEntity<GenBaseClass> queryEntity) {
        queryEntity = Objects.isNull(queryEntity) ? new QueryEntity<>() : queryEntity;
        return page(queryEntity.getPage(), queryEntity.getQueryModel());
    }


}
