package com.easylinker.iot.v2.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by wwhai on 2017/12/25.
 */
@Component
public class EmailSender {
    @Autowired
    JavaMailSender mailSender;

    /**
     * mail_user = "751957846@qq.com"  # 用户名
     * mail_pass = "jhnqisnycdcibdad"  # 口令
     *
     * @throws Exception
     */
    @Async
    public void sendEmail(String toAddress) throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("751957846@qq.com");
        message.setTo(toAddress);
        message.setSubject("[EasyLinker]通知");
        message.setText("你已经成功注册账户!");
        mailSender.send(message);
    }


}
