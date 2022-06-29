package com.ytrue.yadmin.modules.generator.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ytrue.yadmin.core.enums.ResponseCode;
import com.ytrue.yadmin.core.utils.ApiResultResponse;
import com.ytrue.yadmin.core.utils.AssertUtils;
import com.ytrue.yadmin.core.utils.query.QueryEntity;
import com.ytrue.yadmin.modules.generator.model.GenBaseClass;
import com.ytrue.yadmin.modules.generator.service.GenBaseClassService;
import com.ytrue.yadmin.tools.log.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;


/**
 * @author ytrue
 * @date 2022/5/19 16:55
 * @description GenBaseClassController
 */
@RestController
@RequestMapping("gen/baseClass")
@AllArgsConstructor
@Api(tags = "基类管理")
public class GenBaseClassController {

    private final GenBaseClassService genBaseClassService;

    @SysLog
    @PostMapping("page")
    @ApiOperation("分页查询")
    public ApiResultResponse<IPage<GenBaseClass>> page(@RequestBody QueryEntity<GenBaseClass> queryEntity) {
        IPage<GenBaseClass> page = genBaseClassService.page(queryEntity.getPage(), queryEntity.getQueryModel().orderByDesc(GenBaseClass::getId));
        return ApiResultResponse.success(page);
    }


    @GetMapping("list")
    @ApiOperation("列表")
    public ApiResultResponse<List<GenBaseClass>> list() {
        List<GenBaseClass> list = genBaseClassService.list();
        return ApiResultResponse.success(list);
    }

    @GetMapping("detail/{id}")
    @ApiOperation("详情")
    public ApiResultResponse<GenBaseClass> detail(@PathVariable("id") Long id) {
        GenBaseClass baseClass = genBaseClassService.getById(id);
        AssertUtils.notNull(baseClass, ResponseCode.DATA_NOT_FOUND);
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
