package com.easylinker.iot.v2.configure.mqtt.handler;

import com.alibaba.fastjson.JSONObject;
import com.easylinker.iot.v2.model.device.Device;
import com.easylinker.iot.v2.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
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
public class MqttWillMessageHandler implements MessageHandler {
    @Autowired
    DeviceRepository deviceRepository;

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        try {

            String messageString = message.getPayload().toString();
            System.out.println(messageString);
            JSONObject jsonMessage = (JSONObject) JSONObject.parse(messageString);
            /**
             * 在这里 因为用了EMQ的 MySQL插件 所以 把openId当成username 了
             * SQl这么写的 select open_id as username ..........
             */
            String openId = jsonMessage.get("clientid").toString();
            Device device = deviceRepository.findTopByOpenId(openId);
            if (device != null) {
                if (device.isOnline()) {
                    device.setOnline(false);
                    System.out.println("Device " + device.getDeviceName() + " 下线");
                } else if (!device.isOnline()) {
                    device.setOnline(true);
                    System.out.println("Device " + device.getDeviceName() + " 上线");
                }
                deviceRepository.save(device);
                deviceRepository.flush();

            }
        } catch (Exception e) {
            //Json 解析出错
            System.out.println(e.getMessage());
        }


    }
}
