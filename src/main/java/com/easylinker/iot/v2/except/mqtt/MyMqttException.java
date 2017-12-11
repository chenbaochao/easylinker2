package com.easylinker.iot.v2.except.mqtt;

import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * Created by wwhai on 2017/12/11.
 */
public class MyMqttException extends MqttException {
    public MyMqttException(int reasonCode) {
        super(reasonCode);
    }

    public MyMqttException(Throwable cause) {
        super(cause);
    }

    public MyMqttException(int reason, Throwable cause) {
        super(reason, cause);
    }

    @Override
    public int getReasonCode() {
        return super.getReasonCode();
    }

    @Override
    public Throwable getCause() {
        return super.getCause();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
