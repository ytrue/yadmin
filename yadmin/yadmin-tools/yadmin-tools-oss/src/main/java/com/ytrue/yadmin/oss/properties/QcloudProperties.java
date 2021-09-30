package com.ytrue.yadmin.oss.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ytrue
 * @date 2021/7/2 09:12
 * @description QcloudProperties
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties(prefix = "ytrue.oss.qcloud")
public class QcloudProperties extends BaseProperties {

    private String bucket;

    private String region;

    private String secretId;

    private String secretKey;

    private String domain;

    private String fileHost;
}
