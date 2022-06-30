package com.ytrue.yadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author ytrue
 * @date 2021/12/21 15:20
 * @description 后台应用启动类
 * @EnableTransactionManagement 开启事务
 */
@SpringBootApplication
@EnableTransactionManagement
public class AdminApplication {

    /**
     * 入口函数
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
