package com.easylinker.iot.v2.configure.mqtt.handler;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

/**
 * Created by wwhai on 2018/1/2.
 */
@Component
public class ConnectedHandler implements MessageHandler {
    @Override
    public void handleMessage(Message<?> message) throws MessagingException {

    }
}
