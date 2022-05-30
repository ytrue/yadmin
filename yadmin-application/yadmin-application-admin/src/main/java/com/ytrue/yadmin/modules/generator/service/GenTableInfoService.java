package com.ytrue.yadmin.modules.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ytrue.yadmin.modules.generator.model.GenTableInfo;
import com.ytrue.yadmin.modules.generator.model.dto.TableInfoDTO;

import java.util.List;

/**
 * @author ytrue
 * @date 2022/5/27 11:36
 * @description TableInfoService
 */
public interface GenTableInfoService extends IService<GenTableInfo> {

    /**
     * 获取数据源所有的表信息
     *
     * @param id
     * @return
     */
    List<TableInfoDTO> getDataSourceTables(Long id);

}
