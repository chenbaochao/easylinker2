package com.easylinker.iot.v2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 启动入口
 */
@EnableWebSecurity
@EnableScheduling
@EnableWebMvc
@ServletComponentScan
@SpringBootApplication

public class Easylinker2Application {

    public static void main(String[] args) {
        SpringApplication.run(Easylinker2Application.class, args);
    }
}
