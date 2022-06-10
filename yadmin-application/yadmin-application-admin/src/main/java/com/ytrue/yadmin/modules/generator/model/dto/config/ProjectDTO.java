package com.ytrue.yadmin.modules.generator.model.dto.config;

import lombok.Data;

/**
 * @author ytrue
 * @date 2022/6/10 15:16
 * @description 项目信息
 */
@Data
public class ProjectDTO {
    /**
     * 项目包名
     */
    private String packageName;
    /**
     * 项目版本号
     */
    private String version;
    /**
     * 后端生成路径
     */
    private String backendPath;
    /**
     * 前端生成路径
     */
    private String frontendPath;
}
