package com.ytrue.yadmin.modules.generator.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ytrue.yadmin.core.annotation.ApiModelEnumProperty;
import com.ytrue.yadmin.core.utils.ApiResultResponse;
import com.ytrue.yadmin.core.utils.query.QueryEntity;
import com.ytrue.yadmin.modules.generator.model.GenBaseClass;
import com.ytrue.yadmin.modules.generator.service.GenBaseClassService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

/**
 * @author ytrue
 * @date 2022/5/19 16:55
 * @description BaseClassController
 */
@RestController
@RequestMapping("gen/baseClass")
@AllArgsConstructor
@Api(tags = "基类管理")
public class BaseClassController {

    private final GenBaseClassService genBaseClassService;


    @PostMapping("page")
    @ApiOperation("分页查询")
    public ApiResultResponse<IPage<GenBaseClass>> page(@RequestBody QueryEntity<GenBaseClass> queryEntity) {
        IPage<GenBaseClass> page = genBaseClassService.page(queryEntity.getPage(), queryEntity.getQueryModel().orderByDesc(GenBaseClass::getId));
        return ApiResultResponse.success(page);
    }

    @GetMapping("{id}")
    @ApiOperation("详情")
    public ApiResultResponse<GenBaseClass> detail(@PathVariable("id") Long id) {
        GenBaseClass baseClass = genBaseClassService.getById(id);
        return ApiResultResponse.success(baseClass);
    }

    @PostMapping
    @ApiOperation("保存")
    public ApiResultResponse<Object> save(@Valid @RequestBody GenBaseClass genBaseClass) {
        genBaseClassService.save(genBaseClass);
        return ApiResultResponse.success();
    }

    @PutMapping
    @ApiOperation("修改")
    public ApiResultResponse<Object> update(@Valid @RequestBody GenBaseClass genBaseClass) {
        genBaseClassService.updateById(genBaseClass);
        return ApiResultResponse.success();
    }

    @DeleteMapping
    @ApiOperation("删除")
    public ApiResultResponse<Object> delete(@RequestBody Long[] ids) {
        genBaseClassService.removeBatchByIds(Arrays.asList(ids));
        return ApiResultResponse.success();
    }


}
