package com.ytrue.yadmin.modules.generator.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.core.utils.query.QueryEntity;
import com.ytrue.yadmin.modules.generator.dao.GenTableFieldDao;
import com.ytrue.yadmin.modules.generator.model.GenFieldType;
import com.ytrue.yadmin.modules.generator.model.GenTableField;
import com.ytrue.yadmin.modules.generator.service.GenTableFieldService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author ytrue
 * @date 2022/5/19 16:41
 * @description GenTableFieldServiceImpl
 */
@Service
public class GenTableFieldServiceImpl extends ServiceImpl<GenTableFieldDao, GenTableField> implements GenTableFieldService {

    @Override
    public IPage<GenTableField> paginate(QueryEntity<GenTableField> queryEntity) {
        queryEntity = Objects.isNull(queryEntity) ? new QueryEntity<>() : queryEntity;
        return page(queryEntity.getPage(), queryEntity.getQueryModel());
    }
}
