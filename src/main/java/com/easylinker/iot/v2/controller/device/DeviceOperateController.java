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
    public JSONObject addDevice(@RequestBody Map<String, String> deviceParamMap) {
        JSONObject resultJson = new JSONObject();
        if (deviceParamMap == null) {
            resultJson.put("state", 0);
            resultJson.put("message", FailureMessageEnum.INVALID_PARAM);
        } else {
            try {
                String deviceName = deviceParamMap.get("deviceName");
                String deviceDescribe = deviceParamMap.get("deviceDescribe");
                Long groupSerialNumber = Long.parseLong(deviceParamMap.get("groupSerialNumber"));
                DeviceGroup deviceGroup = deviceGroupRepository.findTopBySerialNumber(groupSerialNumber);
                if (deviceGroup != null) {//判断是否存在分组

                    if (deviceRepository.findTopByDeviceName(deviceName) != null) {//判读是否存在
                        resultJson.put("state", 0);
                        resultJson.put("message", FailureMessageEnum.DEVICE_EXIST);
                    } else {
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

                } else {//分组不存在
                    resultJson.put("state", 0);
                    resultJson.put("message", FailureMessageEnum.DEVICE_GROUP_NOT_EXIST);
                }


            } catch (Exception e) {
                e.printStackTrace();
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
