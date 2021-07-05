package com.ytrue.yadmin.config;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.format.DateTimeFormatter;


/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description spring mvc配置类
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 跨域问题
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600);
    }

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String DATA_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 处理日期格式转化
     *
     * @return
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> {
            builder.timeZone("Asia/Shanghai");
            builder.simpleDateFormat(DATA_TIME_FORMAT);
            builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(DATE_FORMAT)));
            builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATA_TIME_FORMAT)));
        };
    }


    /**
     * 放行静态文件,不然favicon.ico会出现404
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}
