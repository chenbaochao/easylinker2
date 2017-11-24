package com.easylinker.iot.v2.model;

import com.easylinker.iot.v2.model.base.BaseEntity;

import javax.persistence.*;

/**
 * Created by wwhai on 2017/11/15.
 */
@Entity
@Table(name = "DEVICE")

public class Device extends BaseEntity {
    private String openId;
    private String deviceName;
    private String deviceDescribe;
    @ManyToOne(targetEntity = DeviceGroup.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private DeviceGroup deviceGroup = new DeviceGroup("DEFAULT_GROUP_"+System.currentTimeMillis(), 1L);

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
