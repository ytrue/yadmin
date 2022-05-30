package com.ytrue.yadmin.modules.generator.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ytrue.yadmin.modules.generator.model.GenFieldType;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ytrue
 * @date 2022/6/13 17:59
 * @description 字段类型管理
 */
@Mapper
public interface GenFieldTypeDao extends BaseMapper<GenFieldType> {

}
