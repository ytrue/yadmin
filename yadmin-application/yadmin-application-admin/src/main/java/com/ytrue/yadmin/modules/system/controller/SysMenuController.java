package com.ytrue.yadmin.modules.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ytrue.yadmin.core.enums.ResponseCode;
import com.ytrue.yadmin.core.utils.ApiResultResponse;
import com.ytrue.yadmin.core.utils.AssertUtils;
import com.ytrue.yadmin.core.utils.query.QueryEntity;
import com.ytrue.yadmin.modules.system.model.SysMenu;
import com.ytrue.yadmin.modules.system.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;


/**
* @author ytrue
* @date 2022-06-29
* @description 菜单管理控制器
*/
@RestController
@RequestMapping("system/sysMenu")
@AllArgsConstructor
@Api(tags = "菜单管理")
public class SysMenuController {

    private final SysMenuService sysMenuService;


    @PostMapping("page")
    @ApiOperation("分页查询")
    public ApiResultResponse<IPage<SysMenu>> page(@RequestBody QueryEntity<SysMenu> queryEntity) {
        IPage<SysMenu> page = sysMenuService.page(queryEntity.getPage(), queryEntity.getQueryModel());
        return ApiResultResponse.success(page);
    }

    @GetMapping("detail/{id}")
    @ApiOperation("详情")
    public ApiResultResponse<SysMenu> detail(@PathVariable("id") Long id) {
        SysMenu data = sysMenuService.getById(id);
        AssertUtils.notNull(data, ResponseCode.DATA_NOT_FOUND);
        return ApiResultResponse.success(data);
    }

    @PostMapping
    @ApiOperation("保存")
    public ApiResultResponse<Object> save(@Valid @RequestBody SysMenu sysMenu) {
        sysMenuService.save(sysMenu);
        return ApiResultResponse.success();
    }

    @PutMapping
    @ApiOperation("修改")
    public ApiResultResponse<Object> update(@Valid @RequestBody SysMenu sysMenu) {
        sysMenuService.updateById(sysMenu);
        return ApiResultResponse.success();
    }

    @DeleteMapping
    @ApiOperation("删除")
    public ApiResultResponse<Object> delete(@RequestBody Long[] ids) {
        sysMenuService.removeBatchByIds(Arrays.asList(ids));
        return ApiResultResponse.success();
    }
}
