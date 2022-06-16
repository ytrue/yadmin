package com.ytrue.yadmin.modules.generator.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ytrue.yadmin.core.enums.ResponseCode;
import com.ytrue.yadmin.core.utils.ApiResultResponse;
import com.ytrue.yadmin.core.utils.AssertUtils;
import com.ytrue.yadmin.core.utils.query.QueryEntity;
import ${package}<#if moduleName??>.${moduleName}</#if>.model<#if subModuleName??>.${subModuleName}</#if>.${ClassName};
import ${package}<#if moduleName??>.${moduleName}</#if>.service<#if subModuleName??>.${subModuleName}</#if>.${ClassName}Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;


/**
* @author ${author}
* @date ${date}
* @description ${tableComment}控制器
*/
@RestController
@RequestMapping("<#if moduleName??>${moduleName}/</#if>${className}")
@AllArgsConstructor
@Api(tags = "${tableComment}")
public class GenBaseClassController {

    private final ${ClassName}Service ${className}Service;


    @PostMapping("page")
    @ApiOperation("分页查询")
    public ApiResultResponse<IPage<${ClassName}>> page(@RequestBody QueryEntity<${ClassName}> queryEntity) {
        IPage<${ClassName}> page = genBaseClassService.page(queryEntity.getPage(), queryEntity.getQueryModel();
        return ApiResultResponse.success(page);
    }

    @GetMapping("{id}")
    @ApiOperation("详情")
    public ApiResultResponse<${ClassName}> detail(@PathVariable("id") Long id) {
        GenBaseClass data = genBaseClassService.getById(id);
        AssertUtils.notNull(data, ResponseCode.DATA_NOT_FOUND);
        return ApiResultResponse.success(data);
    }

    @PostMapping
    @ApiOperation("保存")
    public ApiResultResponse<Object> save(@Valid @RequestBody ${ClassName} ${className}) {
        ${className}Service.save(${className});
        return ApiResultResponse.success();
    }

    @PutMapping
    @ApiOperation("修改")
    public ApiResultResponse<Object> update(@Valid @RequestBody ${ClassName} ${className}) {
        ${className}Service.updateById(${className?uncap_first});
        return ApiResultResponse.success();
    }

    @DeleteMapping
    @ApiOperation("删除")
    public ApiResultResponse<Object> delete(@RequestBody Long[] ids) {
        ${className}Service.removeBatchByIds(Arrays.asList(ids));
        return ApiResultResponse.success();
    }
}