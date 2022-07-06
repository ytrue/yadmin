package com.ytrue.yadmin.modules.generator.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.tools.query.entity.QueryEntity;
import com.ytrue.yadmin.modules.generator.dao.GenFieldTypeDao;
import com.ytrue.yadmin.modules.generator.model.GenDataSource;
import com.ytrue.yadmin.modules.generator.model.GenFieldType;
import com.ytrue.yadmin.modules.generator.service.GenFieldTypeService;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author ytrue
 * @date 2022/5/19 16:41
 * @description GenFieldTypeServiceImpl
 */
@Service
public class GenFieldTypeServiceImpl extends ServiceImpl<GenFieldTypeDao, GenFieldType> implements GenFieldTypeService {

    @Override
    public IPage<GenFieldType> paginate(QueryEntity<GenFieldType> queryEntity) {
        queryEntity = Objects.isNull(queryEntity) ? new QueryEntity<>() : queryEntity;
        return page(queryEntity.getPage(), queryEntity.getQueryModel());
    }
}
