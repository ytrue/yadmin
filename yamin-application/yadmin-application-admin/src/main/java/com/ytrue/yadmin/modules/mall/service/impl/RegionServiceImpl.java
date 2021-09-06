package com.ytrue.yadmin.modules.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.dao.mall.setting.RegionDAO;
import com.ytrue.yadmin.dto.CityDTO;
import com.ytrue.yadmin.dto.ProvinceDTO;
import com.ytrue.yadmin.mapstruct.ProvinceMapper;
import com.ytrue.yadmin.mapstruct.RegionMapper;
import com.ytrue.yadmin.model.mall.setting.Region;
import com.ytrue.yadmin.modules.mall.service.RegionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * @author ytrue
 * @date 2021/9/6 17:22
 * @description 省市区
 */
@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class RegionServiceImpl extends ServiceImpl<RegionDAO, Region> implements RegionService {

    private final RegionMapper regionMapper;

    private final ProvinceMapper provinceMapper;

    @Override
    public HashMap<Integer, ProvinceDTO> treeList() {
        //获得list
        List<Region> regionList = list();
        //转化一下
        List<ProvinceDTO> list = regionMapper.toDto(regionList);
        //返回的tree结构数据
        HashMap<Integer, ProvinceDTO> treeList = new HashMap<>(16);
        //第一次循环
        for (int i = 0; i < list.size(); i++) {
            ProvinceDTO province = list.get(i);
            // 省份
            if (province.getLevel() == 1) {
                treeList.put(province.getId(), province);
                //删除
                list.remove(i);
                //// 因为位置发生改变，所以必须修改i的位置
                i--;
                //第二次循环
                HashMap<Integer, CityDTO> cityMap = new HashMap<>(16);
                for (int j = 0; j < list.size(); j++) {
                    //转成CityDTO
                    CityDTO city = provinceMapper.toDto(list.get(j));
                    if (city.getLevel() == 2 && city.getPid().equals(province.getId())) {
                        cityMap.put(city.getId(), city);
                        treeList.get(province.getId()).setCity(cityMap);
                        //删除
                        list.remove(j);
                        j--;
                        //第三次循环
                        HashMap<Integer, Region> regionMap = new HashMap<>(16);
                        for (int k = 0; k < list.size(); k++) {
                            //转成Region
                            Region region = regionMapper.toEntity(list.get(k));
                            if (region.getLevel() == 3 && region.getPid().equals(city.getId())) {
                                regionMap.put(region.getId(), region);
                                treeList.get(province.getId()).getCity().get(city.getId()).setRegion(regionMap);
                                list.remove(k);
                                k--;
                            }
                        }
                    }
                }
            }
        }
        return treeList;
    }
}
