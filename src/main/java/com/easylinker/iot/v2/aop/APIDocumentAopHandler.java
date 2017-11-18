package com.easylinker.iot.v2.aop;

import com.easylinker.iot.v2.annotation.EasyLinkerApiEntry;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by wwhai on 2017/11/18.
 */
@Aspect
@Component
public class APIDocumentAopHandler {
//    @Pointcut("@annotation(easyLinkerApiEntry)")
//    public void generateAPIDocnment(EasyLinkerApiEntry easyLinkerApiEntry) {
//
//
//    }

    @Before(value = "@annotation(easyLinkerApiEntry)", argNames ="easyLinkerApiEntry" )
    public void before(EasyLinkerApiEntry easyLinkerApiEntry) {

    }


}
