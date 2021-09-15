package com.ytrue.yadmin.oss.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ytrue
 * @date 2021/7/2 09:11
 * @description AliyunProperties
 */

@Data
@ConfigurationProperties(prefix = "ytrue.oss.aliyun")
public class AliyunProperties extends BaseProperties {

    private String bucket;

    private String accessKeyId;

    private String accessKeySecret;

    private String domain;

    private String fileHost;
}


