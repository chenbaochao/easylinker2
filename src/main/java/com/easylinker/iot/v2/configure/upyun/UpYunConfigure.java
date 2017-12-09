package com.easylinker.iot.v2.configure.upyun;

import com.easylinker.iot.v2.model.config.UpYunAccount;
import com.easylinker.iot.v2.repository.UpYunAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * 又拍云的配置
 * Created by wwhai on 2017/12/9.
 */
@Configuration
public class UpYunConfigure {
    @Autowired
    UpYunAccountRepository upYunAccountRepository;
    UpYunAccount upYunAccount;
//
//
//
//
//    public UpYunConfigure() {
//
//        if (upYunAccountRepository.findTopById("EASY_LINKER") == null) {
//            System.out.println("开始配置默认的又拍云账户!");
//            upYunAccount = new UpYunAccount();
//            upYunAccount.setId("EASY_LINKER");
//            upYunAccount.setUsername("EASY_LINKER");
//            upYunAccount.setPassword("EASY_LINKER");
//            upYunAccount.setBucketName("EASY_LINKER");
//            upYunAccount.setApiKey("EASY_LINKER");
//            upYunAccountRepository.save(upYunAccount);
//            System.out.println("又拍云默认账户配置成功，生产环境需要换成自己的!");
//        }
//
//    }生产环境需要换成自己的


}
