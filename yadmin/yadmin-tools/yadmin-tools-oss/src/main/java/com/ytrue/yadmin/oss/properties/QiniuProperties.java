package com.ytrue.yadmin.oss.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ytrue
 * @date 2021/7/2 09:12
 * @description QiniuProperties
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties(prefix = "ytrue.oss.qiniu")
public class QiniuProperties extends BaseProperties {

    private String bucket;

    private String accessKey;

    private String secretKey;

    private String domain;
}
