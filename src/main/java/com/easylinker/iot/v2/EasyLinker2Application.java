package com.easylinker.iot.v2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
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
public class EasyLinker2Application implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(EasyLinker2Application.class);
        springApplication.run(args);

    }

    @Override
    public void run(String... strings) throws Exception {
        /**
         * 在这里要执行一些初始化的操作
         * 1 生成默认的配置文件 写入数据库里面 比如 you拍云 等等
         * 2 生成默认的MQTT用户表 增加默认的数据 √
         * 3 生成默认的权限表  写入ACL √
         * 4 默认配置为Localhost节点
         * 5 设备组默认增加一个组  Default组
         * 6 用户有2个角色（目前） ADMIN  USER  也要初始化
         *
         */

        System.out.println("启动后-----");
    }

}
