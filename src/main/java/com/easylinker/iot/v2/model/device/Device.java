package com.easylinker.iot.v2.model.device;

import com.easylinker.iot.v2.model.base.BaseEntity;
import com.easylinker.iot.v2.utils.MD5Generator;

import javax.persistence.*;

/**
 * ACL表 我直接整合进设备表里面了
 * Created by wwhai on 2017/11/15.
 * CREATE TABLE `mqtt_acl` (
 * `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
 * `allow` int(1) DEFAULT NULL COMMENT '0: deny, 1: allow',
 * `ipaddr` varchar(60) DEFAULT NULL COMMENT 'IpAddress',
 * `username` varchar(100) DEFAULT NULL COMMENT 'Username',
 * `clientid` varchar(100) DEFAULT NULL COMMENT 'ClientId',
 * `access` int(2) NOT NULL COMMENT '1: subscribe, 2: publish, 3: pubsub',
 * `topic` varchar(100) NOT NULL DEFAULT '' COMMENT 'Topic Filter',
 * PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 */
@Entity
@Table(name = "DEVICE")

//MQTT客户端在连接的时候  会发送一个用户名  后台根据这个用户名来查询ACL
/**
 * 新的SQL
 * select allow,ip_address, open_id as username,client_id AS clientid,access, topic
 * from device
 * where  open_id ='4a7cd1a5ad1559944d0b09cfb537bcae';
 *
 */
/**
 *设备  默认的topic 是 device/#  权限是 订阅
 * 管理控制台的是订阅 发布
 */
public class Device extends BaseEntity {

    private String openId = MD5Generator.EncodingMD5(String.valueOf(System.currentTimeMillis()));
    private String clientId = getOpenId();
    private String deviceName;
    private String deviceDescribe;
    private String qrCode;
    private boolean isOnline = false;
    private String ipAddress;
    private Integer allow = 1;
    private Integer access = 3;
    private String topic = "device/" + getOpenId();


    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public String getQrCode() {
        return qrCode;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getAllow() {
        return allow;
    }

    public void setAllow(Integer allow) {
        this.allow = allow;
    }

    public Integer getAccess() {
        return access;
    }

    public void setAccess(Integer access) {
        this.access = access;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    @ManyToOne(targetEntity = DeviceGroup.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private DeviceGroup deviceGroup ;

    public DeviceGroup getDeviceGroup() {
        return deviceGroup;
    }

    public void setDeviceGroup(DeviceGroup deviceGroup) {
        this.deviceGroup = deviceGroup;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceDescribe() {
        return deviceDescribe;
    }

    public void setDeviceDescribe(String deviceDescribe) {
        this.deviceDescribe = deviceDescribe;
    }
}
