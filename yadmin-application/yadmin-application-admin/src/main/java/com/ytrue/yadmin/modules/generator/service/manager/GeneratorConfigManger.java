package com.ytrue.yadmin.modules.generator.service.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ytrue.yadmin.core.enums.ResponseCode;
import com.ytrue.yadmin.core.excptions.BizException;
import com.ytrue.yadmin.core.utils.AssertUtils;
import com.ytrue.yadmin.modules.generator.model.dto.config.GeneratorConfigDTO;
import com.ytrue.yadmin.modules.generator.model.dto.config.TemplateDTO;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author ytrue
 * @date 2022/6/10 15:07
 * @description GeneratorConfigManger
 */
@Component
public class GeneratorConfigManger {


    /**
     * 模板路径
     */
    private static final String TEMPLATE_PATH = "/template/generator/";

    /**
     * 获取生成器的基本信息
     *
     * @return
     */
    public GeneratorConfigDTO getGeneratorConfig() {
        // 模板配置文件
        InputStream configStream = this.getClass().getResourceAsStream(TEMPLATE_PATH + "config.json");
        AssertUtils.notNull(configStream, ResponseCode.TEMPLATE_NOT_FOUND);
        try {
            // 读取模板配置文件
            String configContent = StreamUtils.copyToString(configStream, StandardCharsets.UTF_8);
            Gson gson = new Gson();
            GeneratorConfigDTO generatorConfig = gson.fromJson(configContent, GeneratorConfigDTO.class);
            assert generatorConfig != null;
            // 设置模板内容
            for (TemplateDTO templateInfo : generatorConfig.getTemplates()) {
                // 模板文件
                InputStream templateStream = this.getClass().getResourceAsStream(TEMPLATE_PATH + templateInfo.getTemplateName());

                if (templateStream == null) {
                    throw new BizException("模板文件 " + templateInfo.getTemplateName() + " 不存在");
                }
                // 读取模板内容
                String templateContent = StreamUtils.copyToString(templateStream, StandardCharsets.UTF_8);
                templateInfo.setTemplateContent(templateContent);
            }
            return generatorConfig;
        } catch (IOException e) {
            throw new BizException(ResponseCode.READ_CONFIG_ERROR);
        }
    }
}
