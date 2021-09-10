package com.ytrue.yadmin.modules.mall.controller;

import com.ytrue.yadmin.dto.ProvinceDTO;
import com.ytrue.yadmin.modules.mall.service.RegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author ytrue
 * @date 2021/9/6 17:22
 * @description 省市区
 */
@Api(tags = "省市区")
@RestController
@AllArgsConstructor
@RequestMapping("region")
public class RegionController {

    private final RegionService regionService;


    /**
     * 查询所有,返回tree格式，加入@Cacheable注解，使用返回，提高速度
     *
     * @return
     */
    @GetMapping("tree")
    @ApiOperation(value = "查询所有,返回tree格式")
    public HashMap<Integer, ProvinceDTO> tree() {
        return regionService.treeList();
    }
}
