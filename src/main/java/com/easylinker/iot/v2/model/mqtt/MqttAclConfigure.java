package com.easylinker.iot.v2.model.mqtt;

import com.easylinker.iot.v2.model.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by wwhai on 2017/11/29.
 * create table mqtt_acl
 * (
 * id int not null auto_increment
 * primary key,
 * allow int(1) null,
 * ipaddr varchar(60) null,
 * username varchar(100) null,
 * clientid varchar(100) null,
 * access int(2) not null,
 * topic varchar(100) default '' not null
 * )
 * ;
 * <p>
 * comment on column mqtt_acl.allow is '0: deny, 1: allow'
 * ;
 * <p>
 * comment on column mqtt_acl.ipaddr is 'IpAddress'
 * ;
 * <p>
 * comment on column mqtt_acl.username is 'Username'
 * ;
 * <p>
 * comment on column mqtt_acl.clientid is 'ClientId'
 * ;
 * <p>
 * comment on column mqtt_acl.access is '1: subscribe, 2: publish, 3: pubsub'
 * ;
 * <p>
 * comment on column mqtt_acl.topic is 'Topic Filter'
 * ;
 */

/**
 * 这个是 MQTT的ACL配置
 */
@Entity
@Table
public class MqttAclConfigure extends BaseEntity {


    @Column(name = "clientid")
    private String clientid = "#";
    @Column(name = "ipaddr")
    private String ipaddr = "#";
    @Column(name = "allow")
    private Integer allow = 1;
    @Column(name = "access")
    private Integer access = 3;
    @Column(name = "topic")
    private String topic = "#";
    @Column(name = "username")
    private String username = "$all";

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    public String getIpaddr() {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
