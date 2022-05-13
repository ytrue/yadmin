package com.ytrue.yadmin.tools.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ytrue
 * @date 2022/5/10 17:27
 * @description SecurityProperties
 */
@Data
@ConfigurationProperties(prefix = "ytrue.security")
public class SecurityProperties {

    /**
     * 忽略认证接口
     */
    private Set<String> ignoreAuth = new HashSet<>();

    /**
     * auth 类型 参数名称
     */
    private String authTypeParameterName = "auth_type";

    /**
     * 授权头参数名称
     */
    private String authorizationHeaderParameterName = "Authorization";


    /**
     * 认证的url,也就是集成认证拦截的地址，这个必须要与认证的接口保存一致
     */
    private String authUrl = "login";

    /**
     * token缓存到redis的前缀
     */
    private String tokenCachePrefix = "auth:";

}
