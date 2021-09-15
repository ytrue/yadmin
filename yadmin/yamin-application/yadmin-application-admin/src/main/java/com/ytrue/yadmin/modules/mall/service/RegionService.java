package com.ytrue.yadmin.modules.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ytrue.yadmin.dto.ProvinceDTO;
import com.ytrue.yadmin.model.mall.setting.Region;

import java.util.Map;


/**
 * @author ytrue
 * @date 2021/9/6 17:22
 * @description 省市区
 */
public interface RegionService extends IService<Region> {

    /**
     * 返回 tree数据
     *
     * @return
     */
    Map<Integer, ProvinceDTO> treeList();
}
