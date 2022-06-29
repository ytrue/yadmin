package com.ytrue.yadmin.modules.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ytrue.yadmin.core.enums.ResponseCode;
import com.ytrue.yadmin.core.utils.ApiResultResponse;
import com.ytrue.yadmin.core.utils.AssertUtils;
import com.ytrue.yadmin.core.utils.query.QueryEntity;
import com.ytrue.yadmin.modules.system.model.SysRole;
import com.ytrue.yadmin.modules.system.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;


/**
* @author ytrue
* @date 2022-06-29
* @description 角色控制器
*/
@RestController
@RequestMapping("system/sysRole")
@AllArgsConstructor
@Api(tags = "角色")
public class SysRoleController {

    private final SysRoleService sysRoleService;


    @PostMapping("page")
    @ApiOperation("分页查询")
    public ApiResultResponse<IPage<SysRole>> page(@RequestBody QueryEntity<SysRole> queryEntity) {
        IPage<SysRole> page = sysRoleService.page(queryEntity.getPage(), queryEntity.getQueryModel());
        return ApiResultResponse.success(page);
    }

    @GetMapping("detail/{id}")
    @ApiOperation("详情")
    public ApiResultResponse<SysRole> detail(@PathVariable("id") Long id) {
        SysRole data = sysRoleService.getById(id);
        AssertUtils.notNull(data, ResponseCode.DATA_NOT_FOUND);
        return ApiResultResponse.success(data);
    }

    @PostMapping
    @ApiOperation("保存")
    public ApiResultResponse<Object> save(@Valid @RequestBody SysRole sysRole) {
        sysRoleService.save(sysRole);
        return ApiResultResponse.success();
    }

    @PutMapping
    @ApiOperation("修改")
    public ApiResultResponse<Object> update(@Valid @RequestBody SysRole sysRole) {
        sysRoleService.updateById(sysRole);
        return ApiResultResponse.success();
    }

    @DeleteMapping
    @ApiOperation("删除")
    public ApiResultResponse<Object> delete(@RequestBody Long[] ids) {
        sysRoleService.removeBatchByIds(Arrays.asList(ids));
        return ApiResultResponse.success();
    }
}
