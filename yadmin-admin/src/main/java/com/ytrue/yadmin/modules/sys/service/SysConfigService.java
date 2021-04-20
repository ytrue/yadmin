package com.ytrue.yadmin.modules.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ytrue.yadmin.modules.sys.model.SysConfig;


/**
 * 系统配置信息
 * @author ytrue
 */
public interface SysConfigService extends IService<SysConfig> {

	/**
	 * 根据key，更新value
	 * @param key 参数key
	 * @param value 参数value
	 */
	public void updateValueByKey(String key, String value);

	/**
	 * 删除配置信息
	 * @param ids 配置项id列表
	 */
	public void deleteBatch(Long[] ids);

	/**
	 * 根据key，获取配置的value值
	 * @param key 参数key
	 * @return value
	 */
	public String getValue(String key);

}
