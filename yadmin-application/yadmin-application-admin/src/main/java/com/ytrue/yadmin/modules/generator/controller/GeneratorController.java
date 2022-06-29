package com.ytrue.yadmin.modules.generator.controller;

import com.ytrue.yadmin.core.utils.ApiResultResponse;
import com.ytrue.yadmin.modules.generator.model.GenTableInfo;
import com.ytrue.yadmin.modules.generator.service.GeneratorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ytrue
 * @date 2022/5/27 11:31
 * @description GeneratorController
 */
@RestController
@RequestMapping("gen/generator")
@AllArgsConstructor
@Api(tags = "代码生成")
public class GeneratorController {

    private final GeneratorService generatorService;

    @PostMapping("code")
    @ApiOperation("生成代码")
    private ApiResultResponse<Object> generatorCode(@RequestBody GenTableInfo genTableInfo) {
        generatorService.generatorCode(genTableInfo);
        return ApiResultResponse.success();
    }
}
