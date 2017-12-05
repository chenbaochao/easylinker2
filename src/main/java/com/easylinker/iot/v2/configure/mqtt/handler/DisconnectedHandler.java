package com.easylinker.iot.v2.configure.mqtt.handler;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.integration.handler.AbstractMessageHandler;
import org.springframework.messaging.Message;

/**
 * Created by wwhai on 2017/12/5.
 * 用来监控异常情况
 */
public class DisconnectedHandler extends AbstractMessageHandler implements MqttCallback {

    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("连接失败");

    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }

    @Override
    protected void handleMessageInternal(Message<?> message) throws Exception {

    }
}
