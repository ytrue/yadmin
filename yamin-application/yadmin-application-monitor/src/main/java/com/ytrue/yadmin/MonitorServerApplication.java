package com.ytrue.yadmin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ytrue
 * @date 2021/5/26 10:21
 * @description MonitorServerApplication
 */
@EnableAdminServer
@SpringBootApplication
public class MonitorServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MonitorServerApplication.class, args);
    }
}
