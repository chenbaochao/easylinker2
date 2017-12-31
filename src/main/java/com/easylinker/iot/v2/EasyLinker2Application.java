package com.easylinker.iot.v2;

import com.easylinker.iot.v2.model.config.UpYunAccount;
import com.easylinker.iot.v2.repository.UpYunAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
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
@EnableTransactionManagement
public class EasyLinker2Application implements CommandLineRunner {
    private static Logger logger = LoggerFactory.getLogger(EasyLinker2Application.class);

    @Override
    public void run(String... strings) throws Exception {
        logger.info("项目启动成功");
    }

}
