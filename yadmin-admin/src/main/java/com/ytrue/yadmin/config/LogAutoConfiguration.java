package com.ytrue.yadmin.config;

import com.ytrue.yadmin.log.event.SysLogListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ytrue
 * @date 2021/7/1 17:45
 * @description 日志配置类
 */

@Configuration
public class LogAutoConfiguration {
    /**
     * 自动配置日志监听器组件
     *
     * @param logService
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public SysLogListener sysLogListener(LogService logService) {
        return new SysLogListener(logService::saveLog);
    }

}
