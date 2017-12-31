package com.easylinker.iot.v2.configure.mqtt.handler;

import com.alibaba.fastjson.JSONObject;
import com.easylinker.iot.v2.model.device.Device;
import com.easylinker.iot.v2.model.device.DeviceData;
import com.easylinker.iot.v2.repository.DeviceDataRepository;
import com.easylinker.iot.v2.repository.DeviceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

/**
 * Created by wwhai on 2017/12/30.
 * <p>
 * {
 * "qos"      : 1,
 * "retain"   : false,
 * "unit"     : "CV",
 * "message":"hello world"
 * }
 */

/**
 * 设备通过MQTT连接推送过来的消息，全部是这个类处理
 */
//@Component
public class DeviceMessageReceivedHandler implements MessageHandler {
    Logger logger = LoggerFactory.getLogger(DeviceMessageReceivedHandler.class);
    @Autowired
    DeviceRepository deviceRepository;
    @Autowired
    DeviceDataRepository deviceDataRepository;

    /**
     * 1 解析出PayLoad JSON
     * 2 拿出数据
     * 3 处理
     *
     * @param message
     * @throws MessagingException
     */
    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        try {

            JSONObject payloadJson = JSONObject.parseObject(message.getPayload().toString());
            String deviceMessage = payloadJson.getString("message");
            String unit = payloadJson.getString("unit");
            Boolean retain = payloadJson.getBoolean("retain");
            String openId = message.getHeaders().get("mqtt_topic").toString().split("/")[1];

            Device device = deviceRepository.findTopByOpenId(openId);
            if (device != null) {
                logger.info("openId对应的设备存在");
                if (retain) {
                    logger.info("进行数据持久化");
                    DeviceData deviceData = new DeviceData();
                    deviceData.setUnit(unit);
                    deviceData.setData(deviceMessage);
                    deviceData.setDevice(device);
                    deviceDataRepository.save(deviceData);
                } else {
                    logger.info("不进行数据持久化");
                }
            } else {
                logger.info("openId对应的设备不存在");
            }

        } catch (Exception e) {
            logger.info("消息格式解析出错!" + e.getMessage());
        }


    }


}
