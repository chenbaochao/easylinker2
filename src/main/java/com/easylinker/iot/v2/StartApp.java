package com.easylinker.iot.v2;

import org.springframework.boot.SpringApplication;

/**
 * Created by wwhai on 2017/12/11.
 * 单独用一个类来启动
 */
public class StartApp {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(EasyLinker2Application.class);
        springApplication.run(args);

    }
}
