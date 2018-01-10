package com.easylinker.iot.v2.controller.device;

import com.alibaba.fastjson.JSONObject;
import com.easylinker.iot.v2.constants.FailureMessageEnum;
import com.easylinker.iot.v2.constants.SuccessMessageEnum;
import com.easylinker.iot.v2.model.device.Device;
import com.easylinker.iot.v2.model.device.DeviceGroup;
import com.easylinker.iot.v2.repository.DeviceGroupRepository;
import com.easylinker.iot.v2.repository.DeviceRepository;
import com.easylinker.iot.v2.utils.QRCodeGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 这个控制器用来监控设备
 * Created by wwhai on 2017/12/31.
 */
@Api(value = "设备操作相关", description = "设备增删改查操作", tags = "设备操作")
@RestController
public class DeviceOperateController {
    @Autowired
    DeviceRepository deviceRepository;
    @Autowired
    DeviceGroupRepository deviceGroupRepository;


    /**
     * 设备操作相关
     * 增加设备
     *
     * @param deviceParamMap
     * @return
     */
    @ApiOperation(value = "增加一个设备", notes = "增加一个设备", httpMethod = "POST")
    @RequestMapping(value = "/user/device", method = RequestMethod.POST)
    public JSONObject addDevice(@RequestBody JSONObject deviceParamMap) {
        JSONObject resultJson = new JSONObject();
        if (deviceParamMap == null) {
            resultJson.put("state", 0);
            resultJson.put("message", FailureMessageEnum.INVALID_PARAM);
        } else {
            try {
                String deviceName = deviceParamMap.getString("deviceName");
                String deviceDescribe = deviceParamMap.getString("deviceDescribe");
                Long groupSerialNumber = deviceParamMap.getLong("groupSerialNumber");
                DeviceGroup deviceGroup = deviceGroupRepository.findTopBySerialNumber(groupSerialNumber);
                //判断是否存在分组
                if (deviceGroup != null) {
                    //设备是否存在,默认是不允许创建名字相同的设备的
                    if (deviceRepository.findTopByDeviceName(deviceName) != null) {
                        //出现名字已经存在的情况
                        resultJson.put("state", 0);
                        resultJson.put("message", FailureMessageEnum.DEVICE_EXIST);
                    } else {
                        //创建新设备  挂在指定的分组中
                        Device device = new Device();
                        device.setDeviceName(deviceName);
                        device.setDeviceDescribe(deviceDescribe);
                        device.setDeviceGroup(deviceGroup);
                        device.setQrCode(QRCodeGenerator.generateQRCode(device.getId()));
                        deviceRepository.save(device);
                        resultJson.put("state", 1);
                        resultJson.put("data", device);
                        resultJson.put("message", SuccessMessageEnum.DEVICE_ADD_SUCCESS);

                    }

                } else if (groupSerialNumber == null) {
                    //没有提供分组序列号 就分配进默认分组
                    DeviceGroup defaultGroup = deviceGroupRepository.findTopByName("DEFAULT_GROUP");
                    Device device = new Device();
                    device.setDeviceName(deviceName);
                    device.setDeviceDescribe(deviceDescribe);
                    device.setDeviceGroup(defaultGroup);
                    device.setQrCode(QRCodeGenerator.generateQRCode(device.getId()));
                    deviceRepository.save(device);
                    resultJson.put("state", 1);
                    resultJson.put("data", device);
                    resultJson.put("message", SuccessMessageEnum.DEVICE_ADD_SUCCESS);
                } else {
                    //查不出设备分组
                    resultJson.put("state", 0);
                    resultJson.put("message", FailureMessageEnum.DEVICE_GROUP_NOT_EXIST);
                }


            } catch (Exception e) {
                e.printStackTrace();
                //参数船体不全的情况
                resultJson.put("state", 0);
                resultJson.put("message", FailureMessageEnum.INVALID_PARAM);

            }
        }


        return resultJson;

    }

    /**
     * 查询一个设备信息
     *
     * @param deviceId
     * @return
     */

    @ApiOperation(value = "查找一个设备", notes = "查找增加一个设备", httpMethod = "GET")
    @RequestMapping(value = "/user/device/{deviceId}", method = RequestMethod.GET)
    public JSONObject findDevice(@PathVariable String deviceId) {
        JSONObject resultJson = new JSONObject();
        if (deviceId == null) {
            deviceId = "";
        }
        Device device = deviceRepository.findOne(deviceId);
        if (device != null) {
            resultJson.put("state", 1);
            resultJson.put("message", SuccessMessageEnum.OPERATE_SUCCESS);
            resultJson.put("data", device);

        } else {
            resultJson.put("state", 0);
            resultJson.put("message", FailureMessageEnum.DEVICE_NOT_EXIST);
        }
        return resultJson;

    }

    /**
     * 删除一个设备
     *
     * @param deviceId
     * @return
     */
    @ApiOperation(value = "删除一个设备", notes = "删除一个设备", httpMethod = "DELETE")
    @RequestMapping(value = "/user/device/{deviceId}", method = RequestMethod.DELETE)
    public JSONObject deleteDevice(@PathVariable String deviceId) {
        JSONObject resultJson = new JSONObject();
        Device device = deviceRepository.findOne(deviceId);
        if (device != null) {
            deviceRepository.delete(device);
            resultJson.put("state", 1);
            resultJson.put("message", SuccessMessageEnum.OPERATE_SUCCESS);

        } else {
            resultJson.put("state", 0);
            resultJson.put("message", FailureMessageEnum.DEVICE_NOT_EXIST);
        }
        return resultJson;
    }

    /**
     * 更新一个设备
     *
     * @param deviceParamMap
     * @return
     */

    @ApiOperation(value = "更新一个设备", notes = "更新一个设备", httpMethod = "PUT")
    @RequestMapping(value = "/user/device", method = RequestMethod.PUT)
    public JSONObject updateDevice(Map<String, String> deviceParamMap) {
        JSONObject resultJson = new JSONObject();
        try {
            String deviceId = deviceParamMap.get("deviceId");
            String deviceDescribe = deviceParamMap.get("deviceDescribe");
            String deviceName = deviceParamMap.get("deviceName");
            Long groupSerialNumber = Long.parseLong(deviceParamMap.get("groupSerialNumber"));

            DeviceGroup deviceGroup = deviceGroupRepository.findTopBySerialNumber(groupSerialNumber);
            if (deviceGroup != null) {//判断是否存在分组

                Device device = deviceRepository.findOne(deviceId);
                device.setDeviceName(deviceName);
                device.setDeviceDescribe(deviceDescribe);
                device.setDeviceGroup(deviceGroup);
                deviceRepository.save(device);
                resultJson.put("state", 1);
                resultJson.put("data", device);
                resultJson.put("message", SuccessMessageEnum.DEVICE_ADD_SUCCESS);

            } else {//分组不存在
                resultJson.put("state", 0);
                resultJson.put("message", FailureMessageEnum.DEVICE_GROUP_NOT_EXIST);
            }


        } catch (Exception e) {
            resultJson.put("state", 0);
            resultJson.put("message", FailureMessageEnum.INVALID_PARAM);

        }

        return resultJson;

    }
}
