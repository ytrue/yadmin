package com.ytrue.yadmin;

/**
 * @author ytrue
 * @date 2021/4/16 10:36
 */

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
@SpringBootApplication
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
