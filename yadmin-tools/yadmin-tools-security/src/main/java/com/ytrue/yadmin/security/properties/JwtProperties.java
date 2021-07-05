package com.ytrue.yadmin.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ytrue
 * @date 2021/7/3 21:35
 * @description JwtProperties
 */
@Data
@ConfigurationProperties(prefix = "ytrue.security.jwt")
public class JwtProperties {

    private String signingKey = "dsuifgsdhgvdfgbvusdubhjxzjcbjh@##!@3@4#4545dsfnsdugfsyu";
}
