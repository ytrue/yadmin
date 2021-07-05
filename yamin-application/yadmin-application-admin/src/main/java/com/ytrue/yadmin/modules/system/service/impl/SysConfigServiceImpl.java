package com.ytrue.yadmin.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ytrue.yadmin.dao.system.SysConfigDao;
import com.ytrue.yadmin.model.system.SysConfig;
import com.ytrue.yadmin.modules.system.service.SysConfigService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author ytrue
 */
@Service("sysConfigService")
@AllArgsConstructor
public class SysConfigServiceImpl extends ServiceImpl<SysConfigDao, SysConfig> implements SysConfigService {

	private final SysConfigDao sysConfigDao;

	@Override
	public void updateValueByKey(String key, String value) {
		sysConfigDao.updateValueByKey(key, value);
	}

	@Override
	public void deleteBatch(Long[] ids) {
		sysConfigDao.deleteBatch(ids);
	}

	@Override
	public String getValue(String key) {
		SysConfig config = sysConfigDao.queryByKey(key);
		return config == null ? null : config.getParamValue();
	}
}
