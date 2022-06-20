package com.ytrue.yadmin.modules.generator.service.impl;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.ytrue.yadmin.core.config.JacksonConfigurer;
import com.ytrue.yadmin.core.excptions.BizException;
import com.ytrue.yadmin.modules.generator.dao.GenBaseClassDao;
import com.ytrue.yadmin.modules.generator.dao.GenTableFieldDao;
import com.ytrue.yadmin.modules.generator.dao.GenTableInfoDao;
import com.ytrue.yadmin.modules.generator.model.GenBaseClass;
import com.ytrue.yadmin.modules.generator.model.GenTableField;
import com.ytrue.yadmin.modules.generator.model.GenTableInfo;
import com.ytrue.yadmin.modules.generator.model.dto.config.GeneratorConfigDTO;
import com.ytrue.yadmin.modules.generator.service.GeneratorService;
import com.ytrue.yadmin.modules.generator.service.manager.GeneratorConfigManger;
import freemarker.template.Template;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author ytrue
 * @date 2022/5/27 17:25
 * @description GeneratorServiceImpl
 */
@AllArgsConstructor
@Service
public class GeneratorServiceImpl implements GeneratorService {

    private final GenTableInfoDao genTableInfoDao;
    private final GenTableFieldDao genTableFieldDao;
    private final GeneratorConfigManger generatorConfigManger;
    private final GenBaseClassDao genBaseClassDao;

    @Override
    public void generatorCode() {
        GenTableInfo tableInfo = genTableInfoDao.selectById(10);
        List<GenTableField> columnList = genTableFieldDao
                .selectList(new LambdaQueryWrapper<GenTableField>().eq(GenTableField::getTableId, 10));

        //数据模型
        Map<String, Object> dataModel = new HashMap<>(16);
        //项目信息
        dataModel.put("package", tableInfo.getPackageName());
        dataModel.put("packagePath", tableInfo.getPackageName().replace(".", File.separator));
        dataModel.put("version", tableInfo.getVersion());

        String moduleName = tableInfo.getModuleName();
        if (StrUtil.isBlank(moduleName)) {
            moduleName = null;
        }
        dataModel.put("moduleName", moduleName);

        String subModuleName = tableInfo.getSubModuleName();
        if (StrUtil.isBlank(subModuleName)) {
            subModuleName = null;
        }
        dataModel.put("subModuleName", subModuleName);
        dataModel.put("backendPath", tableInfo.getBackendPath());
        dataModel.put("frontendPath", tableInfo.getFrontendPath());

        //开发者信息
        dataModel.put("author", tableInfo.getAuthor());
        dataModel.put("email", tableInfo.getEmail());
        dataModel.put("datetime", LocalDateTime.now().format(DateTimeFormatter.ofPattern(JacksonConfigurer.DEFAULT_DATE_TIME_FORMAT)));
        dataModel.put("date", LocalDate.now().format(DateTimeFormatter.ofPattern(JacksonConfigurer.DEFAULT_DATE_FORMAT)));

        //表信息
        dataModel.put("tableName", tableInfo.getTableName());
        dataModel.put("tableComment", tableInfo.getTableComment());
        dataModel.put("ClassName", tableInfo.getClassName());
        dataModel.put("className", StrUtil.lowerFirst(tableInfo.getClassName()));
        dataModel.put("columnList", columnList);

        GenTableField pkTableField = columnList.stream().filter(GenTableField::getIsPk).findFirst().orElse(null);
        dataModel.put("pk", pkTableField);

        //导入的包列表
        Set<String> imports = columnList.stream().map(GenTableField::getPackageName).filter(StringUtils::isNotBlank).collect(Collectors.toSet());
        dataModel.put("imports", imports);

        //基类
        dataModel.put("baseClassEntity", null);
        if (tableInfo.getBaseclassId() != null) {
            GenBaseClass baseClass = genBaseClassDao.selectById(tableInfo.getBaseclassId());
            dataModel.put("baseClassEntity", baseClass);
        }


        //代码生成器信息
        GeneratorConfigDTO generatorConfig = generatorConfigManger.getGeneratorConfig();

        //渲染模板并输出
        generatorConfig.getTemplates().forEach(template -> {
            dataModel.put("templateName", template.getTemplateName());
            String content = getRenderedTemplateContent(template.getTemplateContent(), dataModel);
            String path = getRenderedTemplateContent(template.getGeneratorPath(), dataModel);
            //生成文件
            FileUtil.writeString(content, new File(path), "utf-8");
        });
    }

    /**
     * 获取模板渲染后的内容
     *
     * @param content   模板内容
     * @param dataModel 数据模型
     */
    public static String getRenderedTemplateContent(String content, Map<String, Object> dataModel) {
        if (dataModel.isEmpty()) {
            return content;
        }

        StringReader reader = new StringReader(content);
        StringWriter sw = new StringWriter();
        try {
            //渲染模板
            String templateName = dataModel.getOrDefault("templateName", "generator").toString();
            Template template = new Template(templateName, reader, null, "utf-8");
            template.process(dataModel, sw);
        } catch (Exception e) {
            throw new BizException("渲染模板失败，请检查模板语法", e);
        }

        content = sw.toString();

        IOUtils.closeQuietly(reader);
        IOUtils.closeQuietly(sw);

        return content;
    }
}
