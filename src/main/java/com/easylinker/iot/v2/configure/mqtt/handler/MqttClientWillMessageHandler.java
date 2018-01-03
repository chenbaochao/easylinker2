package com.easylinker.iot.v2.configure.mqtt.handler;

import com.alibaba.fastjson.JSONObject;
import com.easylinker.iot.v2.model.device.Device;
import com.easylinker.iot.v2.repository.DeviceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

/**
 * Created by wwhai on 2017/11/30.
 * 在这里处理上线下线操作
 */

/**
 * JSON 数据结构
 * {
 * "ipaddress":"127.0.0.1",
 * "protocol":4,
 * "clientid":"37c6f5557c1e47c1bc7c82bdd1c91b47",
 * "clean_sess":true,
 * "connack":0,
 * "username":"37c6f5557c1e47c1bc7c82bdd1c91b47",
 * "ts":1512057028
 * }
 */
@Component
public class MqttClientWillMessageHandler implements MessageHandler {
    Logger logger = LoggerFactory.getLogger(MqttClientWillMessageHandler.class);
    @Autowired
    DeviceRepository deviceRepository;


    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        try {
            String messageString = message.getPayload().toString();
            JSONObject jsonMessage = (JSONObject) JSONObject.parse(messageString);
            /**
             * 在这里 因为用了EMQ的 MySQL插件 所以 把openId当成username 了
             * SQl这么写的 select open_id as username ..........
             */
            Device device = deviceRepository.findTopByOpenId(jsonMessage.get("username").toString());
            MessageHeaders headers = message.getHeaders();
            String topic = headers.get("mqtt_topic", String.class);
            if (topic.endsWith("/connected")) {
                logger.info("设备[" + device.getOpenId() + "]上线了！");
                device.setOnline(true);
                deviceRepository.save(device);

            } else if (topic.endsWith("/disconnected")) {
                logger.info("设备[" + device.getOpenId() + "]下线了！");
                device.setOnline(false);
                deviceRepository.save(device);
            }


        } catch (Exception e) {
            logger.error("解析客户端提交的消息时出现了格式错误!");
        }


    }


}
