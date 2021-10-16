package com.ytrue.yadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author ytrue
 * @date 2021/10/11 14:07
 * @description ChatApplication
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan(value = {
        "com.ytrue.yadmin.dao",
})
public class ChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatApplication.class, args);
    }
}
