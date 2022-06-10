package com.ytrue.yadmin.modules.generator.model.dto.config;

import lombok.Data;

/**
 * @author ytrue
 * @date 2022/6/10 15:16
 * @description 模板信息
 */
@Data
public class TemplateDTO {
    /**
     * 模板名称
     */
    private String templateName;
    /**
     * 模板内容
     */
    private String templateContent;
    /**
     * 生成代码的路径
     */
    private String generatorPath;
}
