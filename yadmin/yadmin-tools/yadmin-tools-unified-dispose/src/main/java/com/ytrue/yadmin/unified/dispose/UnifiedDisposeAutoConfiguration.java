package com.ytrue.yadmin.unified.dispose;


import com.ytrue.yadmin.unified.dispose.properties.UnifiedDisposeResponseDataProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @author ytrue
 * @date 2021/7/11 12:26
 * @description 自动配置类
 */
@EnableConfigurationProperties({
        UnifiedDisposeResponseDataProperties.class
})
@PropertySource(value = "classpath:dispose.properties", encoding = "UTF-8")
public class UnifiedDisposeAutoConfiguration {
}
