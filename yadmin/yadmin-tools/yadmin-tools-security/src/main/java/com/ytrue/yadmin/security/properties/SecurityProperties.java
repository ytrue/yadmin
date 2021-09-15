package com.ytrue.yadmin.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ytrue
 * @date 2021/7/3 21:25
 * @description SecurityProperties
 */
@Data
@ConfigurationProperties(prefix = "ytrue.security")
public class SecurityProperties {

    private String clientId;

    private String clientSecret;

    private Integer accessTokenValiditySeconds = 864000;

    private Integer refreshTokenValiditySeconds = 864000;

    private String scopes = "all";
}
