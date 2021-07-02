package com.ytrue.yadmin.oss.parameter.enums;

import lombok.Getter;

/**
 * @author ytrue
 * @date 2021/6/13 18:58
 * @description Local upload枚举
 */
@Getter
public enum UploadType {


    /**
     * 阿里云
     */
    ALIYUN("aliyun1"),

    /**
     * 七牛云
     */
    QINIU("qiniu1"),

    /**
     * 腾讯云
     */
    QCLOUD("qcloud1"),

    /**
     * 本地
     */
    LOCAL("local1");

    /**
     * 类型
     */
    private final String type;

    UploadType(String mark) {
        this.type = mark;
    }
}
