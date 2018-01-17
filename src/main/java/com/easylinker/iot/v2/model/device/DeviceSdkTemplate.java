package com.easylinker.iot.v2.model.device;

import com.easylinker.iot.v2.model.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 * Created by wwhai on 2018/1/17.
 * 动态生成SDK的模板
 */
@Entity
@Table(name = "DEVICE_SDK_TYPE")
public class DeviceSdkTemplate extends BaseEntity {
    /**
     * 属性：
     * 1 名称
     * 2 模板编号
     * 3 语言类别
     * 4 模板
     */

    private String name;
    private Long serialNumber;

    @Enumerated(EnumType.STRING)
    private SdkType sdkType;

    private String template;

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public SdkType getSdkType() {
        return sdkType;
    }

    public void setSdkType(SdkType sdkType) {
        this.sdkType = sdkType;
    }
}
