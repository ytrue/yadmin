package com.ytrue.yadmin.modules.generator.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ytrue.yadmin.tools.query.entity.QueryEntity;
import com.ytrue.yadmin.modules.generator.model.GenFieldType;

/**
 * @author ytrue
 * @date 2022/5/19 16:40
 * @description GenFieldTypeService
 */
public interface GenFieldTypeService extends IService<GenFieldType> {

    /**
     * 分页查询
     *
     * @param queryEntity
     * @return
     */
    IPage<GenFieldType> paginate(QueryEntity<GenFieldType> queryEntity);
}
