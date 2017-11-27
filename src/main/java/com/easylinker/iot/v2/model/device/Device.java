package com.easylinker.iot.v2.model.device;

import com.easylinker.iot.v2.model.base.BaseEntity;
import com.easylinker.iot.v2.utils.MD5Generator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by wwhai on 2017/11/15.
 */
@Entity
@Table(name = "DEVICE")

public class Device extends BaseEntity {
    private String openId = MD5Generator.EncodingMD5(String.valueOf(System.currentTimeMillis()));
    private String deviceName;
    private String deviceDescribe;
    private String qrCode;

//    @OneToMany(targetEntity = DeviceData.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<DeviceData> deviceDataList;
//
//    public List<DeviceData> getDeviceDataList() {
//        return deviceDataList;
//    }
//
//    public void setDeviceDataList(List<DeviceData> deviceDataList) {
//        this.deviceDataList = deviceDataList;
//    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    @ManyToOne(targetEntity = DeviceGroup.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private DeviceGroup deviceGroup = new DeviceGroup("DEFAULT_GROUP_" + System.currentTimeMillis(), 1L);

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
