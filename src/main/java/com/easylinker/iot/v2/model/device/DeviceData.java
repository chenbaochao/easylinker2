package com.easylinker.iot.v2.model.device;

import com.easylinker.iot.v2.model.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by wwhai on 2017/11/25.
 * 设备产生的数据的实体
 */
@Entity
@Table(name = "DEVICE_DATA")
/**
 * 数值  单位    比如  :1(Kg)
 */
public class DeviceData extends BaseEntity {
    private String data;
    private String unit;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
