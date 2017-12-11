package com.easylinker.iot.v2;

import com.easylinker.iot.v2.model.config.UpYunAccount;
import com.easylinker.iot.v2.repository.UpYunAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
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
public class EasyLinker2Application implements CommandLineRunner {
    private static Logger logger = LoggerFactory.getLogger(EasyLinker2Application.class);
    @Autowired
    UpYunAccountRepository upYunAccountRepository;


    @Override
    public void run(String... strings) throws Exception {
        initialUpYunAccount();
        logger.info("项目启动成功");
    }

    /**
     * 初始化UpYUN的账户
     */
    public void initialUpYunAccount() {

        UpYunAccount upYunAccount = upYunAccountRepository.findTopById("EASY_LINKER");
        if (upYunAccount == null) {
            logger.info("开始初始化又拍云的账户数据");
            upYunAccount = new UpYunAccount();
            upYunAccount.setId("EASY_LINKER");
            upYunAccount.setApiKey("EASY_LINKER");
            upYunAccount.setBucketName("EASY_LINKER");
            upYunAccount.setUsername("EASY_LINKER");
            upYunAccount.setPassword("EASY_LINKER");
            upYunAccountRepository.save(upYunAccount);
            logger.info("又拍云初始化账户增加成功，请在生产环境替换成自己的账户.");
        }

    }


}
