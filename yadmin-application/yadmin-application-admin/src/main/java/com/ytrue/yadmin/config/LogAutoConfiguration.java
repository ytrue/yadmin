package com.ytrue.yadmin.config;

import com.ytrue.yadmin.tools.log.enitiy.OperationLog;
import com.ytrue.yadmin.tools.log.event.SysLogListener;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author ytrue
 * @date 2022/6/1 15:40
 * @description 日志配置类
 */
@Slf4j
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


    @Slf4j
    @Component
    @AllArgsConstructor
    public static class LogService {
        public void saveLog(OperationLog operationLog) {
//            log.info("操作日志开启监听了....start");
//            log.info(operationLog.toString());
//            log.info("操作日志开启监听了....end");
        }
    }

}
