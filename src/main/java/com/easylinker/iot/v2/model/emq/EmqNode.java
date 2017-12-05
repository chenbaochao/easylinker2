package com.easylinker.iot.v2.model.emq;

import com.easylinker.iot.v2.model.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by wwhai on 2017/12/5.
 * 节点
 */
@Entity
@Table(name = "EMQ_NODE")
public class EmqNode extends BaseEntity {
    private String ipAddress = "localhost";
    private String name = "localhost";
    private String cookie = "default_cookie";

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
}
