package com.easylinker.iot.v2.configure.mqtt;

import com.easylinker.iot.v2.configure.mqtt.handler.DeviceMessageReceivedHandler;
import com.easylinker.iot.v2.configure.mqtt.handler.MqttClientWillMessageHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;

/**
 * Created by wwhai on 2017/11/29.
 */
@Configuration
public class MqttConfigure {
    /**
     * 默认连接的是本机的EMQ节点 这里为了开发 写死了  后面会把这个HOST地址配置进数据库里面
     */
    @Value("${easylinker.emq.host}")
    private   String LOCALHOST_EMQ_URL;
    private   String LOCALHOST_EMQ_USERNAME = "EASY_LINKER";
    private   String LOCALHOST_EMQ_PASSWORD = "EASY_LINKER";
    //默认监听所有节点的所有客户端的信息
    private   String WILL_TOPIC = "$SYS/brokers/+/clients/+/#";

    /**
     * mqtt 的工厂  用来创建mqtt连接
     *
     * @return
     */

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        /**
         * 后面这里的配置要从数据库里面加载
         */
        factory.setServerURIs(LOCALHOST_EMQ_URL);
        factory.setUserName(LOCALHOST_EMQ_USERNAME);
        factory.setPassword(LOCALHOST_EMQ_PASSWORD);
        return factory;
    }

    /**
     * 这个Bean用来配置处理接受消息的处理器
     *
     * @return
     */
    @Bean("mqttInbound")
    public MessageProducerSupport mqttInbound() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(
                "mqttInbound",
                mqttClientFactory());
        adapter.addTopic(WILL_TOPIC);
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        return adapter;
    }

    /**
     * 用来处理消息适配器
     *
     * @return
     */
    @Bean("mqttInFlow")
    public IntegrationFlow mqttInFlow() {
        return IntegrationFlows.from(mqttInbound())
                .handle(new MqttClientWillMessageHandler())
                .get();
    }

    /**
     *
     */


    /**
     * 上下线处理
     *
     * @return
     */
    @Bean("mqttMessageInBound")
    public MessageProducerSupport mqttMessageInBound() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(
                "mqttMessageInBound",
                mqttClientFactory());
        adapter.addTopic("device/#");
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        return adapter;
    }

    /**
     * 用来处理消息适配器
     *
     * @return
     */
    @Bean("mqttMessageInFlow")
    public IntegrationFlow mqttMessageInFlow() {
        return IntegrationFlows.from(mqttMessageInBound())
                .handle(new DeviceMessageReceivedHandler())
                .get();
    }


}
