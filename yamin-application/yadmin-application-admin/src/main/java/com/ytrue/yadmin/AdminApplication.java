package com.ytrue.yadmin;

/**
 * @author ytrue
 * @date 2021/4/16 10:36
 *
 * @EnableCaching 开启缓存
 * @EnableTransactionManagement 开启事务
 * @EnableGlobalMethodSecurity 开启Spring方法级安全
 * @MapperScan 指定要变成实现类的接口所在的包，然后包下面的所有接口在编译之后都会生成相应的实现类
 */

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableCaching
@SpringBootApplication
@EnableTransactionManagement
@EnableGlobalMethodSecurity(prePostEnabled = true)
@MapperScan(value = {
        "com.ytrue.yadmin.dao",
        "com.ytrue.yadmin.modules.quartz.dao",
        "com.ytrue.yadmin.modules.system.dao",
})
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
