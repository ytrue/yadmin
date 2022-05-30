package com.ytrue.yadmin.modules.generator.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ytrue.yadmin.modules.generator.model.GenDataSource;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ytrue
 * @date 2022/6/13 17:59
 * @description 数据源管理
 */
@Mapper
public interface GenDataSourceDao extends BaseMapper<GenDataSource> {

}
