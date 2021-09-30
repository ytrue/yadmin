package com.ytrue.yadmin.oss.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ytrue
 * @date 2021/7/2 15:46
 * @description 本地配置
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties(prefix = "ytrue.oss.local")
public class LocalProperties extends BaseProperties {

    private String domain;

    private String fileHost;
}
