package com.ytrue.yadmin.unified.dispose.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ytrue
 * @date 2021/7/11 12:24
 * @description 全局异常
 */
@Data
@ConfigurationProperties(prefix = "ytrue.unified.dispose.exception")
public class UnifiedDisposeExceptionProperties {

    /**
     * 是否开启
     */
    private Boolean enabled = true;

    /**
     * 是否生产环境
     */
    private Boolean production = false;
}
