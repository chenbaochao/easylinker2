package com.easylinker.iot.v2.configure.mqtt;

import com.easylinker.iot.v2.configure.mqtt.handler.DeviceMessageReceivedHandler;
import com.easylinker.iot.v2.configure.mqtt.handler.MqttClientWillMessageHandler;
import com.easylinker.iot.v2.configure.mqtt.handler.MyMqttPahoMessageDrivenChannelAdapter;
import com.easylinker.iot.v2.model.device.Device;
import com.easylinker.iot.v2.repository.DeviceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    Logger logger = LoggerFactory.getLogger(MqttConfigure.class);

    @Autowired
    DeviceRepository deviceRepository;

    /**
     * 默认连接的是本机的EMQ节点 这里为了开发 写死了
     */
    @Value("${easylinker.emq.host}")
    private String EMQ_URL;
    private final String EMQ_USERNAME = "EASY_LINKER";
    private final String EMQ_PASSWORD = "EASY_LINKER";
    //默认监听所有节点的所有客户端的信息
    private final String DEVICE_CONNECTED = "$SYS/brokers/+/clients/+/connected";
    private final String DEVICE_DISCONNECTED = "$SYS/brokers/+/clients/+/disconnected";
    private final String MONITOR_TOPIC = "$SYS/brokers/+/clients/+/#";
    private final String DEVICE_TOPIC = "device/#";

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


        if (deviceRepository.findTopByDeviceName(EMQ_USERNAME) == null) {
            logger.info("创建默认连接账号....");
            Device device = new Device();
            device.setOpenId(EMQ_USERNAME);
            device.setClientId(EMQ_PASSWORD);
            device.setTopic(MONITOR_TOPIC);
            device.setDeviceName(EMQ_USERNAME);
            device.setDeviceDescribe("MONITOR");
            deviceRepository.save(device);
            logger.info("创建默认连接账号成功");
        }


        factory.setServerURIs(EMQ_URL);
        factory.setUserName(EMQ_USERNAME);
        factory.setPassword(EMQ_PASSWORD);

        return factory;
    }

    /**
     * 这个Bean用来配置处理接受消息的处理器
     *
     * @return
     */

    @Bean
    public MessageProducerSupport mqttMessageHandler() {
        logger.info("加载消息监听插件....");

        MqttPahoMessageDrivenChannelAdapter adapter = new MyMqttPahoMessageDrivenChannelAdapter(
                "mqttMessageHandler",
                mqttClientFactory());
        adapter.addTopic(DEVICE_TOPIC);//监控消息
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(2);
        logger.info("加载消息监听插件成功");

        return adapter;
    }

    @Bean
    public IntegrationFlow mqttMessageInFlow() {
        logger.info("加载客户端消息处理器....");

        return IntegrationFlows.from(mqttMessageHandler())
                .handle(new DeviceMessageReceivedHandler())
                .get();
    }


    @Bean
    public MessageProducerSupport mqttClientHandler() {
        logger.info("加载客户端监听插件....");


        MqttPahoMessageDrivenChannelAdapter adapter = new MyMqttPahoMessageDrivenChannelAdapter(
                "mqttClientHandler",
                mqttClientFactory());
        adapter.addTopic(DEVICE_CONNECTED);//监控上线通知
        adapter.addTopic(DEVICE_DISCONNECTED);//监控下线通知

        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(2);
        logger.info("加载客户端监听插件成功");

        return adapter;
    }

    /**
     * 用来处理消息适配器
     *
     * @return
     */

    @Bean
    public IntegrationFlow mqttClientInFlow() {
        logger.info("加载客户端处理器....");
        return IntegrationFlows.from(mqttClientHandler())
                .handle(new MqttClientWillMessageHandler())
                .get();
    }


}
