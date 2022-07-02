package com.ytrue.yadmin.modules.generator.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ytrue.yadmin.core.utils.query.Fields;
import com.ytrue.yadmin.modules.generator.model.GenBaseClass;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * @author ytrue
 * @date 2022/6/13 17:59
 * @description 基类管理
 */
public interface GenBaseClassDao extends BaseMapper<GenBaseClass> {

    List<GenBaseClass> queryAll(Fields fields1, Fields fields2, @Param("id") Integer id);

    void deleteAll(Fields fields1, Fields fields2, @Param("id") Integer id);
}
