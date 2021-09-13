package com.ytrue.yadmin.modules.mall.controller;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ytrue.yadmin.enums.StrPool;
import com.ytrue.yadmin.log.annotation.SysLog;
import com.ytrue.yadmin.model.mall.setting.Express;
import com.ytrue.yadmin.modules.mall.service.ExpressService;
import com.ytrue.yadmin.search.SearchModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author ytrue
 * @date 2021/9/13 11:12
 * @description 物流公司记录表
 */
@Api(tags = "物流公司")
@RestController
@AllArgsConstructor
@RequestMapping("mall/express")
public class ExpressController {

    private final ExpressService expressService;

    @PostMapping("page")
    @ApiOperation("分页查询")
    public IPage<Express> page(@RequestBody SearchModel<Express> searchModel) {
        return expressService
                .page(
                        searchModel.getPage(),
                        searchModel.getQueryModel()
                                .lambda()
                                .orderByDesc(Express::getSort)
                                .orderByDesc(Express::getExpressId));
    }


    @SysLog
    @PostMapping
    @ApiOperation("保存物流公司")
    public void save(@Valid @RequestBody Express express) {
        expressService.save(express);
    }


    @GetMapping("{expressId}/info")
    @ApiOperation("物流公司信息")
    public Express info(@PathVariable("expressId") Long expressId) {
        Express express = expressService.getById(expressId);
        Assert.notNull(express, StrPool.DATA_DOES_NOT_EXIST.getMessage());
        return express;
    }

    @SysLog
    @PutMapping
    @ApiOperation("修改物流公司")
    public void update(@Valid @RequestBody Express express) {
        expressService.updateById(express);
    }

    @SysLog
    @DeleteMapping
    @ApiOperation("删除物流公司")
    public void delete(@RequestBody List<Long> expressIds) {
        expressService.removeByIds(expressIds);
    }

}
