package com.ytrue.yadmin.dto;

import com.ytrue.yadmin.model.mall.setting.Region;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * @author ytrue
 * @date 2021/9/6 17:28
 * @description 省级
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ProvinceDTO extends Region {

    private static final long serialVersionUID = 2396053620700955988L;

    private Map<Integer, CityDTO> city;

}
