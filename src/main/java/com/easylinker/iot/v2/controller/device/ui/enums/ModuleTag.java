package com.easylinker.iot.v2.controller.device.ui.enums;

/**
 * Created by wwhai on 2018/1/17.
 * temperature and humidity
 * 主界面显示的插件模块的类型（暂定）
 * 1 TEMPERATURE 温度
 * 2 HUMIDITY    湿度
 * 3 LOCATION    地理位置 :经纬度
 * 4 PRESSURE    压强
 * 5 VALUE       纯粹的数值型
 * 6 SWITCHER    单值开关
 * 7 STRING      字符串类型
 * 8 FILE        文件类型
 * ....后面有了特殊的再增加
 */
public enum ModuleTag {
    TEMPERATURE,
    HUMIDITY,
    LOCATION,
    PRESSURE,
    VALUE,
    SWITCHER,
    STRING,
    FILE
}
