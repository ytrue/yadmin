package com.ytrue.yadmin.oss.parameter.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ytrue
 * @date 2021/7/2 15:46
 * @description 本地配置
 */
@Data
@ConfigurationProperties(prefix = "ytrue.oss.local")
public class LocalProperties extends BaseProperties {

    private String domain;

    private String fileHost;
}
