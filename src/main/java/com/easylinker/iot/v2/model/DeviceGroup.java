package com.easylinker.iot.v2.model;

import com.easylinker.iot.v2.model.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by wwhai on 2017/11/23.
 * 设备的分组
 */
@Entity
@Table(name = "DEVICE_GROUP")
/**
 * 名称  序列号编码
 * 默认是编号为1名称为默认分组
 */
public class DeviceGroup extends BaseEntity {
    private String name;
    private Long serialNumber = System.currentTimeMillis();

    @JsonIgnore
    @OneToMany(targetEntity = Device.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Device> deviceList;

    public List<Device> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }

    public DeviceGroup(String name, Long serialNumber) {
        this.name = name;
        this.serialNumber = serialNumber;
    }

    public DeviceGroup() {

    }

    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
