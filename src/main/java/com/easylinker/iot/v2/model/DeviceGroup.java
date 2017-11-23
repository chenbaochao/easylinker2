package com.easylinker.iot.v2.model;

import com.easylinker.iot.v2.model.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

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
    private Integer serialNumber;

    public DeviceGroup(String name, Integer serialNumber) {
        this.name = name;
        this.serialNumber = serialNumber;
    }

    public DeviceGroup() {

    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
