package com.ytrue.yadmin.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ytrue.yadmin.modules.sys.dao.SysConfigMapper;
import com.ytrue.yadmin.modules.sys.model.SysConfig;
import com.ytrue.yadmin.modules.sys.service.SysConfigService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author lgh
 */
@Service("sysConfigService")
@AllArgsConstructor
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements SysConfigService {

	private final SysConfigMapper sysConfigMapper;

	@Override
	public void updateValueByKey(String key, String value) {
		sysConfigMapper.updateValueByKey(key, value);
	}

	@Override
	public void deleteBatch(Long[] ids) {
		sysConfigMapper.deleteBatch(ids);
	}

	@Override
	public String getValue(String key) {
		SysConfig config = sysConfigMapper.queryByKey(key);
		return config == null ? null : config.getParamValue();
	}
}
