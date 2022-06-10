package com.ytrue.yadmin.modules.generator.model.dto.config;

import lombok.Data;

import java.util.List;

/**
 * @author ytrue
 * @date 2022/6/10 15:14
 * @description 生成器配置
 */
@Data
public class GeneratorConfigDTO {

    /**
     * 项目信息
     */
    private ProjectDTO project;

    /**
     * 开发者信息
     */
    private DeveloperDTO developer;

    /**
     * 模板信息
     */
    private List<TemplateDTO> templates;
}
