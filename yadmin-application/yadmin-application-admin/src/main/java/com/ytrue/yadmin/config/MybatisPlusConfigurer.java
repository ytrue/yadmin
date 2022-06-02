package com.ytrue.yadmin.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
 * @author ytrue
 * @date 2022/4/13 15:45
 * @description mybatis plus的相关配置
 * @MapperScan 指定要变成实现类的接口所在的包，然后包下面的所有接口在编译之后都会生成相应的实现类
 */
@Configuration
@MapperScan(value = {"com.ytrue.yadmin.modules.*.dao"})
public class MybatisPlusConfigurer implements MetaObjectHandler {


    /**
     * 乐观锁
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return mybatisPlusInterceptor;
    }


    /**
     * 新增时自动填充
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        setFieldValByName("createTime", LocalDateTime.now(), metaObject);

    }

    /**
     * 更新时自动填充
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }
}