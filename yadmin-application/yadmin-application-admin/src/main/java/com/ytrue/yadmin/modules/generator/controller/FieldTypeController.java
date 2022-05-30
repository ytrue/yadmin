package com.ytrue.yadmin.modules.generator.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ytrue.yadmin.core.utils.ApiResultResponse;
import com.ytrue.yadmin.core.utils.query.QueryEntity;
import com.ytrue.yadmin.modules.generator.model.GenFieldType;
import com.ytrue.yadmin.modules.generator.service.GenFieldTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * @author ytrue
 * @date 2022/5/27 11:27
 * @description FieldTypeController
 */
@RestController
@RequestMapping("gen/fieldType")
@AllArgsConstructor
@Api(tags = "字段类型管理")
public class FieldTypeController {
    private final GenFieldTypeService genFieldTypeService;

    @PostMapping("page")
    @ApiOperation("分页查询")
    public ApiResultResponse<IPage<GenFieldType>> page(@RequestBody QueryEntity<GenFieldType> queryEntity) {
        IPage<GenFieldType> page = genFieldTypeService.page(queryEntity.getPage(), queryEntity.getQueryModel().orderByDesc(GenFieldType::getId));
        return ApiResultResponse.success(page);
    }

    @GetMapping("list")
    @ApiOperation("列表")
    public ApiResultResponse<List<GenFieldType>> list() {
        List<GenFieldType> list = genFieldTypeService.list();
        return ApiResultResponse.success(list);
    }

    @GetMapping("{id}")
    @ApiOperation("详情")
    public ApiResultResponse<GenFieldType> detail(@PathVariable("id") Long id) {
        GenFieldType genFieldType = genFieldTypeService.getById(id);
        return ApiResultResponse.success(genFieldType);
    }


    @PostMapping
    @ApiOperation("保存")
    public ApiResultResponse<Object> save(@Valid @RequestBody GenFieldType genFieldType) {
        genFieldTypeService.save(genFieldType);
        return ApiResultResponse.success();
    }

    @PutMapping
    @ApiOperation("修改")
    public ApiResultResponse<Object> update(@Valid @RequestBody GenFieldType genFieldType) {
        genFieldTypeService.updateById(genFieldType);
        return ApiResultResponse.success();
    }

    @DeleteMapping
    @ApiOperation("删除")
    public ApiResultResponse<Object> delete(@RequestBody Long[] ids) {
        genFieldTypeService.removeBatchByIds(Arrays.asList(ids));
        return ApiResultResponse.success();
    }
}
