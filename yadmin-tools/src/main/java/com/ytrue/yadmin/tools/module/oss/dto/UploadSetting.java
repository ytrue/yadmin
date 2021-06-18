package com.ytrue.yadmin.tools.module.oss.dto;

import lombok.Data;

/**
 * @author ytrue
 * @date 2021/6/13 18:58
 * @description Local 配置聚合
 */
@Data
public class UploadSetting {

    /**
     * 默认引擎
     */
    private String defaultEngine;

    /**
     * 引擎集合
     */
    private Engine engine;
}
