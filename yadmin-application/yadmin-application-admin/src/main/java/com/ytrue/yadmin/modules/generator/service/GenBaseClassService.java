package com.ytrue.yadmin.modules.generator.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ytrue.yadmin.core.utils.query.QueryEntity;
import com.ytrue.yadmin.modules.generator.model.GenBaseClass;

/**
 * @author ytrue
 * @date 2022/5/19 16:39
 * @description GenBaseClassService
 */
public interface GenBaseClassService extends IService<GenBaseClass> {

    /**
     * 分页查询
     *
     * @param queryEntity
     * @return
     */
    IPage<GenBaseClass> paginate(QueryEntity<GenBaseClass> queryEntity);
}
