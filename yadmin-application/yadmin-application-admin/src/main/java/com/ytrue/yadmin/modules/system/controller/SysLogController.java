package com.ytrue.yadmin.modules.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ytrue.yadmin.core.enums.ResponseCode;
import com.ytrue.yadmin.core.utils.ApiResultResponse;
import com.ytrue.yadmin.core.utils.AssertUtils;
import com.ytrue.yadmin.core.utils.query.QueryEntity;
import com.ytrue.yadmin.modules.system.model.SysLog;
import com.ytrue.yadmin.modules.system.service.SysLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;


/**
* @author ytrue
* @date 2022-06-29
* @description 操作日志控制器
*/
@RestController
@RequestMapping("system/sysLog")
@AllArgsConstructor
@Api(tags = "操作日志")
public class SysLogController {

    private final SysLogService sysLogService;


    @PostMapping("page")
    @ApiOperation("分页查询")
    public ApiResultResponse<IPage<SysLog>> page(@RequestBody QueryEntity<SysLog> queryEntity) {
        IPage<SysLog> page = sysLogService.page(queryEntity.getPage(), queryEntity.getQueryModel());
        return ApiResultResponse.success(page);
    }

    @GetMapping("detail/{id}")
    @ApiOperation("详情")
    public ApiResultResponse<SysLog> detail(@PathVariable("id") Long id) {
        SysLog data = sysLogService.getById(id);
        AssertUtils.notNull(data, ResponseCode.DATA_NOT_FOUND);
        return ApiResultResponse.success(data);
    }

    @PostMapping
    @ApiOperation("保存")
    public ApiResultResponse<Object> save(@Valid @RequestBody SysLog sysLog) {
        sysLogService.save(sysLog);
        return ApiResultResponse.success();
    }

    @PutMapping
    @ApiOperation("修改")
    public ApiResultResponse<Object> update(@Valid @RequestBody SysLog sysLog) {
        sysLogService.updateById(sysLog);
        return ApiResultResponse.success();
    }

    @DeleteMapping
    @ApiOperation("删除")
    public ApiResultResponse<Object> delete(@RequestBody Long[] ids) {
        sysLogService.removeBatchByIds(Arrays.asList(ids));
        return ApiResultResponse.success();
    }
}
