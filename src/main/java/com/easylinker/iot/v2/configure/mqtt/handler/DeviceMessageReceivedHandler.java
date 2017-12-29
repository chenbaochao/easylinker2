package com.easylinker.iot.v2.configure.mqtt.handler;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

/**
 * Created by wwhai on 2017/12/30.
 */

/**
 * [
 * payload={
 * "clientid":"1779fce02ce9c8e5d6d0478866085ce1",
 * "username":"1779fce02ce9c8e5d6d0478866085ce1",
 * "reason":"closed","ts":1514566333},
 * headers={
 * mqtt_retained=false,
 * mqtt_qos=1,
 * id=2713f9d0-4480-1b4e-34e2-26281a7aceb3,
 * mqtt_topic=$SYS/brokers/emq@127.0.0.1/clients/1779fce02ce9c8e5d6d0478866085ce1/disconnected,
 * mqtt_duplicate=false,
 * timestamp=1514566333894
 * }
 * ]
 */

public class DeviceMessageReceivedHandler implements MessageHandler {
    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        System.out.println("处理客户端消息:" + message);

    }
}
