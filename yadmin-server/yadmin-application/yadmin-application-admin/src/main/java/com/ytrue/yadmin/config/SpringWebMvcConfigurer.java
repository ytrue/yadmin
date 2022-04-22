package com.ytrue.yadmin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ytrue
 * @date 2022/1/20 14:33
 * @description SpringWebMvcConfigurer spring mvc 配置文件
 */
@EnableWebMvc
@Configuration
public class SpringWebMvcConfigurer implements WebMvcConfigurer {

    /**
     * doc.html是在jar包里的，需要使用资源处理器注册静态资源，不然会404
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
