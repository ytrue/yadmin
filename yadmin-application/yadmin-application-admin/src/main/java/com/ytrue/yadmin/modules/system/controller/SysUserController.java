package com.ytrue.yadmin.modules.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ytrue.yadmin.core.enums.ResponseCode;
import com.ytrue.yadmin.core.utils.ApiResultResponse;
import com.ytrue.yadmin.core.utils.AssertUtils;
import com.ytrue.yadmin.tools.query.entity.QueryEntity;
import com.ytrue.yadmin.modules.system.model.SysUser;
import com.ytrue.yadmin.modules.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;


/**
 * @author ytrue
 * @date 2022-06-29
 * @description 系统用户控制器
 */
@RestController
@RequestMapping("system/sysUser")
@AllArgsConstructor
@Api(tags = "系统用户")
public class SysUserController {

    private final SysUserService sysUserService;


    @PostMapping("page")
    @ApiOperation("分页查询")
    public ApiResultResponse<IPage<SysUser>> page(@RequestBody(required = false) QueryEntity<SysUser> queryEntity) {
        IPage<SysUser> page = sysUserService.paginate(queryEntity);
        return ApiResultResponse.success(page);
    }

    @GetMapping("detail/{id}")
    @ApiOperation("详情")
    public ApiResultResponse<SysUser> detail(@PathVariable("id") Long id) {
        SysUser data = sysUserService.getById(id);
        AssertUtils.notNull(data, ResponseCode.DATA_NOT_FOUND);
        return ApiResultResponse.success(data);
    }

    @PostMapping
    @ApiOperation("保存")
    public ApiResultResponse<Object> save(@Valid @RequestBody SysUser sysUser) {
        sysUserService.save(sysUser);
        return ApiResultResponse.success();
    }

    @PutMapping
    @ApiOperation("修改")
    public ApiResultResponse<Object> update(@Valid @RequestBody SysUser sysUser) {
        sysUserService.updateById(sysUser);
        return ApiResultResponse.success();
    }

    @DeleteMapping
    @ApiOperation("删除")
    public ApiResultResponse<Object> delete(@RequestBody Long[] ids) {
        sysUserService.removeBatchByIds(Arrays.asList(ids));
        return ApiResultResponse.success();
    }
}
