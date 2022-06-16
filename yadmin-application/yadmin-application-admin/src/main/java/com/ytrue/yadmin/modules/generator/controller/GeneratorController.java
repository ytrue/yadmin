package com.ytrue.yadmin.modules.generator.controller;

import com.ytrue.yadmin.modules.generator.service.GeneratorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ytrue
 * @date 2022/5/27 11:31
 * @description GeneratorController
 */
@RestController
@RequestMapping("gen")
@AllArgsConstructor
@Api(tags = "代码生成")
public class GeneratorController {


    private final GeneratorService generatorService;

    @GetMapping("generator/code")
    @ApiOperation("生成代码")
    private void generatorCode() {
        generatorService.generatorCode();
    }
}
