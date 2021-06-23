package com.ytrue.yadmin.modules.system.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ytrue.yadmin.modules.system.model.SysConfig;
import org.apache.ibatis.annotations.Param;


/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 系统配置信息
 */
public interface SysConfigDao extends BaseMapper<SysConfig> {

    /**
     * 根据key，查询系统配置信息
     *
     * @param key key
     * @return SysConfig
     */
    SysConfig queryByKey(String key);

    /**
     * 根据key，更新value
     *
     * @param key
     * @param value
     * @return 更新成功条数
     */
    int updateValueByKey(@Param("key") String key, @Param("value") String value);


    /**
     * 批量删除系统配置
     *
     * @param ids 系统配置信息数组
     */
    void deleteBatch(@Param("ids") Long[] ids);

}
