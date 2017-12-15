package com.easylinker.iot.v2.configure.mqtt.handler;

import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;

/**
 * Created by wwhai on 2017/12/11.
 */
public class MyMqttPahoMessageDrivenChannelAdapter extends MqttPahoMessageDrivenChannelAdapter {

    public MyMqttPahoMessageDrivenChannelAdapter(String clientId, MqttPahoClientFactory clientFactory, String... topic) {
        super(clientId, clientFactory, topic);
    }

    @Override
    public synchronized void connectionLost(Throwable cause) {
        //super.connectionLost(cause);
        System.out.println("------------------------------"+cause.getMessage());
    }



}
