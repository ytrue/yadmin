package com.ytrue.yadmin.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ytrue.yadmin.system.model.SysConfig;


/**
 * 系统配置信息
 *
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 系统用户控制器
 */
public interface SysConfigService extends IService<SysConfig> {

    /**
     * 根据key，更新value
     *
     * @param key   参数key
     * @param value 参数value
     */
    void updateValueByKey(String key, String value);

    /**
     * 删除配置信息
     *
     * @param ids 配置项id列表
     */
    void deleteBatch(Long[] ids);

    /**
     * 根据key，获取配置的value值
     *
     * @param key 参数key
     * @return value
     */
    String getValue(String key);

}
