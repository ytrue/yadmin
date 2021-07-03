package com.ytrue.yadmin.oss.dto;

import lombok.Data;

/**
 * @author ytrue
 * @date 2021/7/2 22:08
 * @description 上传配置设置，聚合
 */
@Data
public class UploadSetting {

    /**
     * 默认引擎
     */
    private String defaultEngine;

    /**
     * 引擎聚合
     */
    private Engine engine;
}
