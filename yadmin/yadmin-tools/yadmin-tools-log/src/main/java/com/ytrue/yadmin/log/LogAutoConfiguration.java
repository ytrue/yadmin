package com.ytrue.yadmin.log;

import com.ytrue.yadmin.log.aspect.SysLogAspect;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author ytrue
 * @date 2021/7/1 14:42
 * @description 日志自动配置
 * 启动条件：
 * 1，存在web环境
 * 2，配置文件中      ytrue.log.enabled=true
 * 3，配置文件中不存在：ytrue.log.enabled 值
 */

@EnableAsync
@Configuration
@AllArgsConstructor
@ConditionalOnWebApplication
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
