package com.ytrue.yadmin.modules.generator.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ytrue.yadmin.core.utils.ApiResultResponse;
import com.ytrue.yadmin.modules.generator.model.GenTableField;
import com.ytrue.yadmin.modules.generator.service.GenTableFieldService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ytrue
 * @date 2022/6/13 16:30
 * @description GenTableFieldController
 */
@RestController
@RequestMapping("gen/tableField")
@AllArgsConstructor
@Api(tags = "表字段管理")
public class GenTableFieldController {

    private final GenTableFieldService genTableFieldService;


    @GetMapping("list/{id}")
    @ApiOperation("根据表id获取字段列表")
    public ApiResultResponse<List<GenTableField>> listByTableId(@PathVariable("id") Integer id) {
        List<GenTableField> list = genTableFieldService
                .list(new LambdaQueryWrapper<GenTableField>().eq(GenTableField::getTableId, id));
        return ApiResultResponse.success(list);
    }

    @PostMapping("batchUpdate")
    @ApiOperation("批量修改")
    public ApiResultResponse<Object> batchUpdate(@RequestBody List<GenTableField> genTableFields) {
        genTableFieldService.updateBatchById(genTableFields);
        return ApiResultResponse.success();
    }
}
