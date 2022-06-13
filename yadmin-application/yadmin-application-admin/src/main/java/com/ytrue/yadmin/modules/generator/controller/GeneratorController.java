package com.ytrue.yadmin.modules.generator.controller;

import com.ytrue.yadmin.modules.generator.service.GeneratorService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
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

}
