package com.easylinker.iot.v2.configure.upyun;

import main.java.com.UpYun;
import main.java.com.upyun.FormUploader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 又拍云的配置
 * Created by wwhai on 2017/12/9.
 */
@Configuration
public class UpYunConfigure {
    @Value("${upyun.account.bucketname}")
    String bucketName;
    @Value("${upyun.account.username}")
    String username;
    @Value("${upyun.account.password}")
    String password;
    @Value("${upyun.account.apiKey}")
    String apiKey;

    @Bean
    public FormUploader getFormUploader() {
        return new
                FormUploader(
                bucketName,
                apiKey,
                s -> "Up云集成失败!请检查配置"
        );
    }

    @Bean
    public UpYun getUpYun() {
        return new
                UpYun(
                bucketName,
                username,
                password
        );
    }

}
