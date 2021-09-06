package com.ytrue.yadmin.mapstruct;

import com.ytrue.yadmin.base.BaseMapper;

import com.ytrue.yadmin.dto.ProvinceDTO;
import com.ytrue.yadmin.model.mall.setting.Region;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author ytrue
 * @date 2021/9/6 17:34
 * @description 省级转区
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RegionMapper extends BaseMapper<ProvinceDTO, Region> {
}
