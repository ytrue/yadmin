package com.ytrue.yadmin.tools.log;

import com.ytrue.yadmin.tools.log.aspect.SysLogAspect;
import com.ytrue.yadmin.tools.log.properties.LogProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ytrue
 * @date 2022/6/1 15:02
 * @description LogAutoConfiguration
 * 启动条件：
 * 1，存在web环境
 * 2，配置文件中      ytrue.log.enabled=true ,默认是开启的
 */
@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties(LogProperties.class)
@ConditionalOnProperty(name = "ytrue.log.enabled", havingValue = "true", matchIfMissing = true)
public class LogAutoConfiguration {

    /**
     * 注册
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public SysLogAspect sysLogAspect() {
        return new SysLogAspect();
    }
}
