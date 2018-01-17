package com.easylinker.iot.v2.controller.device.ui.module;

import com.easylinker.iot.v2.controller.device.ui.enums.ModuleTag;
import com.easylinker.iot.v2.model.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 * Created by wwhai on 2018/1/17.
 * 设备功能模块页面  类似于支付宝的界面
 * 温度模块
 * 1231321
 * <p>
 * ┍━━━━┳━━━━━┳━━━━━┓
 * ┃    ┃     ┃     ┃
 * ┃  1 ┃  2  ┃  3  ┃
 * ┃━━━━┃━━━━━┃━━━━━┃
 * ┃  4 ┃  5  ┃  6  ┃
 * ┃    ┃     ┃     ┃
 * ┣━━━━╋━━━━━╋━━━━━┫
 * ┃  7 ┃ 8   ┃  9  ┃
 * ┃    ┃     ┃     ┃
 * ┗━━━━┻━━━━━┻━━━━━┛
 * 配合一个根据模块序列号获取功能的控制器
 */
@Entity
@Table(name = "Device_Module_FunctionUI")
public class DeviceModuleFunctionUI extends BaseEntity {
    private String name;
    private Long serialNumber;
    @Enumerated(EnumType.STRING)
    private ModuleTag moduleTag;

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

    public ModuleTag getModuleTag() {
        return moduleTag;
    }

    public void setModuleTag(ModuleTag moduleTag) {
        this.moduleTag = moduleTag;
    }
}
