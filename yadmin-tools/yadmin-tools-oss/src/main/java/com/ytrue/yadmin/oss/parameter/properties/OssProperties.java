package com.ytrue.yadmin.oss.parameter.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ytrue
 * @date 2021/7/2 13:57
 * @description OssProperties
 */
@Data
@ConfigurationProperties(prefix = "ytrue.oss")
public class OssProperties {
    private String engine;
}
