package com.ytrue.yadmin.tools.oss.dto;

import lombok.Data;

/**
 * @author ytrue
 * @date 2021/6/13 18:58
 * @description 阿里云oss配置
 */
@Data
public class Aliyun {

    private String bucket;

    private String accessKeyId;

    private String accessKeySecret;

    private String domain;
}
