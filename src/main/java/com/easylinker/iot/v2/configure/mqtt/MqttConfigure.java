package com.easylinker.iot.v2.configure.mqtt;

import com.easylinker.iot.v2.configure.mqtt.handler.DeviceMessageReceivedHandler;
import com.easylinker.iot.v2.configure.mqtt.handler.MqttClientWillMessageHandler;
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
 * <p>
 * 上线消息
 * {
 * "clientid":"8305132bc868fa613dfa2fa3eac6053a",
 * "username":"8305132bc868fa613dfa2fa3eac6053a",
 * "ipaddress":"192.168.3.74",
 * "clean_sess":true,
 * "protocol":4,
 * "connack":0,
 * "ts":1514995030},
 * headers={mqtt_retained=false,
 * mqtt_qos=1,
 * id=d6f0b5c9-3d2e-f0b7-903b-05eb419fc5be,
 * mqtt_topic=$SYS/brokers/emq@127.0.0.1/clients/8305132bc868fa613dfa2fa3eac6053a/connected,
 * mqtt_duplicate=false,
 * timestamp=1514995030144
 * }
 * <p>
 * 上线消息
 * {
 * "clientid":"8305132bc868fa613dfa2fa3eac6053a",
 * "username":"8305132bc868fa613dfa2fa3eac6053a",
 * "ipaddress":"192.168.3.74",
 * "clean_sess":true,
 * "protocol":4,
 * "connack":0,
 * "ts":1514995030},
 * headers={mqtt_retained=false,
 * mqtt_qos=1,
 * id=d6f0b5c9-3d2e-f0b7-903b-05eb419fc5be,
 * mqtt_topic=$SYS/brokers/emq@127.0.0.1/clients/8305132bc868fa613dfa2fa3eac6053a/connected,
 * mqtt_duplicate=false,
 * timestamp=1514995030144
 * }
 * <p>
 * 上线消息
 * {
 * "clientid":"8305132bc868fa613dfa2fa3eac6053a",
 * "username":"8305132bc868fa613dfa2fa3eac6053a",
 * "ipaddress":"192.168.3.74",
 * "clean_sess":true,
 * "protocol":4,
 * "connack":0,
 * "ts":1514995030},
 * headers={mqtt_retained=false,
 * mqtt_qos=1,
 * id=d6f0b5c9-3d2e-f0b7-903b-05eb419fc5be,
 * mqtt_topic=$SYS/brokers/emq@127.0.0.1/clients/8305132bc868fa613dfa2fa3eac6053a/connected,
 * mqtt_duplicate=false,
 * timestamp=1514995030144
 * }
 */
/**
 * 上线消息
 * {
 * "clientid":"8305132bc868fa613dfa2fa3eac6053a",
 * "username":"8305132bc868fa613dfa2fa3eac6053a",
 * "ipaddress":"192.168.3.74",
 * "clean_sess":true,
 * "protocol":4,
 * "connack":0,
 * "ts":1514995030},
 * headers={mqtt_retained=false,
 * mqtt_qos=1,
 * id=d6f0b5c9-3d2e-f0b7-903b-05eb419fc5be,
 * mqtt_topic=$SYS/brokers/emq@127.0.0.1/clients/8305132bc868fa613dfa2fa3eac6053a/connected,
 * mqtt_duplicate=false,
 * timestamp=1514995030144
 * }
 */

/**
 * 下线
 *{
 * "clientid":"8305132bc868fa613dfa2fa3eac6053a",
 * "username":"8305132bc868fa613dfa2fa3eac6053a",
 * "reason":"closed","ts":1514995139
 * },
 * headers={
 * mqtt_retained=false,
 * mqtt_qos=1,
 * id=661f362c-92f8-c551-ffb3-3c0afcaee5db,
 * mqtt_topic=$SYS/brokers/emq@127.0.0.1/clients/8305132bc868fa613dfa2fa3eac6053a/disconnected,
 * mqtt_duplicate=false,
 * timestamp=1514995139594
 * }
 * ]
 Device 树莓派4 下线
 */
@Configuration
public class MqttConfigure {
    Logger logger = LoggerFactory.getLogger(MqttConfigure.class);

    @Autowired
    DeviceRepository deviceRepository;
    /**
     * 默认连接的是本机的EMQ节点 这里为了开发 写死了  后面会把这个HOST地址配置进数据库里面
     */
    @Value("${easylinker.emq.host}")
    String LOCALHOST_EMQ_URL;
    String LOCALHOST_EMQ_USERNAME = "EASY_LINKER";
    String LOCALHOST_EMQ_PASSWORD = "EASY_LINKER";


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
        if (deviceRepository.findTopByDeviceName(LOCALHOST_EMQ_USERNAME) == null) {
            logger.info("创建默认连接账号....");
            Device device = new Device();
            device.setOpenId(LOCALHOST_EMQ_USERNAME);
            device.setClientId(LOCALHOST_EMQ_USERNAME);
            device.setDeviceName(LOCALHOST_EMQ_USERNAME);
            device.setDeviceDescribe("EasyLinker监控器");
            device.setTopic("$SYS/brokers/+/clients/+/#");
            deviceRepository.save(device);
            logger.info("创建默认连接账号成功");
        }


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
    @Bean("mqttClientInbound")
    public MessageProducerSupport mqttClientInbound() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(
                "mqttClientInbound",
                mqttClientFactory());
        adapter.addTopic("$SYS/brokers/+/clients/+/#");//监控设备消息上下线
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
    @Bean("mqttClientInFlow")
    public IntegrationFlow mqttClientInFlow() {
        return IntegrationFlows.from(mqttClientInbound())
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
        adapter.addTopic("device/#");//监控设备消息
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
