package com.ytrue.yadmin.modules.generator.controller;

import com.ytrue.yadmin.core.utils.ApiResultResponse;
import com.ytrue.yadmin.modules.generator.model.dto.request.TableFieldUpdateRequest;
import com.ytrue.yadmin.modules.generator.service.GenTableFieldService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ytrue
 * @date 2022/6/13 16:30
 * @description GenTableFieldController
 */
@RestController
@RequestMapping("gen/tableInfo")
@AllArgsConstructor
@Api(tags = "表字段管理")
public class GenTableFieldController {

    private final GenTableFieldService genTableFieldService;


    @PutMapping
    @ApiOperation("修改")
    public ApiResultResponse<Object> update(@RequestBody TableFieldUpdateRequest tableFieldUpdateRequest) {
        return ApiResultResponse.success();
    }
}
