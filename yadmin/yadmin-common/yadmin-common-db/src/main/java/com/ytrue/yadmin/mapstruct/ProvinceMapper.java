package com.ytrue.yadmin.mapstruct;

import com.ytrue.yadmin.base.BaseMapper;
import com.ytrue.yadmin.dto.CityDTO;
import com.ytrue.yadmin.dto.ProvinceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author ytrue
 * @date 2021/9/6 17:54
 * @description 省级转市级
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProvinceMapper extends BaseMapper<CityDTO, ProvinceDTO> {
}
