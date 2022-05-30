package com.ytrue.yadmin.modules.generator.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ytrue.yadmin.modules.generator.model.GenTableInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ytrue
 * @date 2022/6/13 17:59
 * @description 表信息
 */
@Mapper
public interface GenTableInfoDao extends BaseMapper<GenTableInfo> {

}
