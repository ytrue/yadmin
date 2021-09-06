package com.ytrue.yadmin.modules.mall.controller;

import com.ytrue.yadmin.dto.ProvinceDTO;
import com.ytrue.yadmin.modules.mall.service.RegionService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
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
     * 返回tree格式的,这个要加入redis 缓存
     *
     * @return
     */
    @PostMapping("tree")
    public HashMap<Integer, ProvinceDTO> tree() {
        return regionService.treeList();
    }
}
