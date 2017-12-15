package com.easylinker.iot.v2.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by wwhai on 2017/12/15.
 */
@Aspect
@Component
public class MqttExceptionAopHandler {

    @Pointcut("execution(*  org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule.start())")
    public void connect() {}

    @After("connect()")
    public void doException(JoinPoint joinPoint) {
        System.out.println("出异常了");

    }
}
