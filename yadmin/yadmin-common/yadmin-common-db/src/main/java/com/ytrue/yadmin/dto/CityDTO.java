package com.ytrue.yadmin.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.ytrue.yadmin.model.mall.setting.Region;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * @author ytrue
 * @date 2021/9/6 17:29
 * @description 市级
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CityDTO extends Region {

    private static final long serialVersionUID = -7779406077284876994L;

    @TableField(exist = false)
    private Map<Integer, Region> region;

}
