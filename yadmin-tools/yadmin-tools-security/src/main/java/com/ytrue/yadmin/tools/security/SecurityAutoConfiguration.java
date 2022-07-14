package com.ytrue.yadmin.tools.security;

import com.ytrue.yadmin.tools.security.properties.JwtProperties;
import com.ytrue.yadmin.tools.security.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author ytrue
 * @date 2022/5/12 14:05
 * @description SecurityAutoConfiguration
 */
@Configurable
@EnableConfigurationProperties({SecurityProperties.class, JwtProperties.class})
public class SecurityAutoConfiguration {
}
