package com.ytrue.yadmin.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description mybatis plus配置类
 */
@Configuration
public class MybatisPlusConfig {
    /**
     * 配置分页
     *
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }


    /**
     * 配置乐观锁
     *
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }


    /**
     * 自定义mybatisPlus填充类
     */
    @Component
    static class MyMetaObjectHandler implements MetaObjectHandler {
        @Override
        public void insertFill(MetaObject metaObject) {
            setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
            setFieldValByName("createTime", LocalDateTime.now(), metaObject);

        }

        @Override
        public void updateFill(MetaObject metaObject) {
            setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
    }
}
