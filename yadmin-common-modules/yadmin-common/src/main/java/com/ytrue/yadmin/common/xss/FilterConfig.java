package com.ytrue.yadmin.common.xss;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;

/**
 * @author ytrue
 * @date 2021/2/28 12:47
 * @description xss过滤器配置类
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<XssFilter> filterRegistration() {
        FilterRegistrationBean<XssFilter> registration = new FilterRegistrationBean<>();
        //添加过滤器
        registration.setFilter(new XssFilter());
        //设置过滤路径，/*所有路径
        registration.addUrlPatterns("/*");
        registration.setName("xssFilter");
        //设置优先级
        registration.setOrder(Integer.MAX_VALUE);
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        return registration;
    }
}
