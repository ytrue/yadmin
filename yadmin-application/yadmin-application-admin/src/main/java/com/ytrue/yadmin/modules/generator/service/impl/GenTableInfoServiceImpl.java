package com.ytrue.yadmin.modules.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.modules.generator.dao.GenTableInfoDao;
import com.ytrue.yadmin.modules.generator.model.GenTableInfo;
import com.ytrue.yadmin.modules.generator.service.GenTableInfoService;
import com.ytrue.yadmin.modules.generator.model.dto.TableInfoDTO;
import com.ytrue.yadmin.modules.generator.service.manager.DbManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ytrue
 * @date 2022/5/19 16:41
 * @description GenTableInfoServiceImpl
 */
@Service
@AllArgsConstructor
public class GenTableInfoServiceImpl extends ServiceImpl<GenTableInfoDao, GenTableInfo> implements GenTableInfoService {

    private final DbManager dbManager;

    @Override
    public List<TableInfoDTO> getDataSourceTables(Long id) {
        return dbManager.getDataSourceTables(id);
    }
}
