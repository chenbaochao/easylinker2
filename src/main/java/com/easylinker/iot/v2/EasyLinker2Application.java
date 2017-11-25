package com.easylinker.iot.v2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 启动入口
 */
@EnableAspectJAutoProxy
@EnableWebSecurity
@EnableScheduling
@EnableWebMvc
@ServletComponentScan
@SpringBootApplication
@EnableWebSocket
@EnableAsync
@EnableSwagger2
public class EasyLinker2Application {


    public static void main(String[] args) {
        SpringApplication.run(EasyLinker2Application.class, args);

    }
}
