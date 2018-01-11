package com.easylinker.iot.v2.controller.device;

import com.alibaba.fastjson.JSONObject;
import com.easylinker.iot.v2.constants.FailureMessageEnum;
import com.easylinker.iot.v2.constants.SuccessMessageEnum;
import com.easylinker.iot.v2.model.device.Device;
import com.easylinker.iot.v2.model.device.DeviceGroup;
import com.easylinker.iot.v2.model.user.AppUser;
import com.easylinker.iot.v2.repository.DeviceGroupRepository;
import com.easylinker.iot.v2.repository.DeviceRepository;
import com.easylinker.iot.v2.utils.ReturnResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by wwhai on 2017/12/15.
 */
@Api(value = "设备组管理", description = "用户的设备组管理", tags = "设备组操作")
@RestController

public class DeviceGroupController {
    @Autowired
    DeviceGroupRepository deviceGroupRepository;
    @Autowired
    DeviceRepository deviceRepository;
    /**
     * 设备组处理相关
     */

    /**
     * 增加分组
     */

    @ApiOperation(value = "增加分组", notes = "增加分组", httpMethod = "POST")
    @RequestMapping(value = "/user/device/group", method = RequestMethod.POST)
    public JSONObject addGroup(@RequestBody Map<String, String> groupNameParamMap) throws Exception {
        AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JSONObject resultJson = new JSONObject();
        try {
            String groupName = groupNameParamMap.get("groupName");


            if (groupName.equals("") || (groupName == null)) {
                resultJson.put("state", 0);
                resultJson.put("message", FailureMessageEnum.INVALID_PARAM);
            } else {
                /**
                 * 是否存在
                 */
                if (deviceGroupRepository.findTopByName(groupName) != null) {
                    resultJson.put("state", 0);
                    resultJson.put("message", SuccessMessageEnum.DEVICE_GROUP_EXIST);
                } else {
                    DeviceGroup deviceGroup = new DeviceGroup();
                    deviceGroup.setName(groupName);
                    deviceGroup.setAppUser(appUser);
                    deviceGroupRepository.save(deviceGroup);
                    resultJson.put("state", 1);
                    resultJson.put("data", deviceGroup);
                    resultJson.put("message", SuccessMessageEnum.DEVICE_GROUP_ADD_SUCCESS);
                }

            }
        } catch (Exception e) {
            resultJson.put("state", 0);
            resultJson.put("message", FailureMessageEnum.INVALID_PARAM);

        }

        return resultJson;


    }

    /**
     * 删除分组
     */
    @ApiOperation(value = "删除分组", notes = "删除分组", httpMethod = "DELETE")
    @RequestMapping(value = "/user/device/group/{groupId}", method = RequestMethod.DELETE)
    public JSONObject deleteGroup(@PathVariable String groupId) {
        JSONObject resultJson = new JSONObject();
        if (groupId.equals("")) {
            resultJson.put("state", 0);
            resultJson.put("message", FailureMessageEnum.INVALID_PARAM);
        } else {
            /**
             * 是否存在
             */
            DeviceGroup deviceGroup = deviceGroupRepository.findOne(groupId);
            if (deviceGroup == null) {
                resultJson.put("state", 0);
                resultJson.put("message", FailureMessageEnum.DEVICE_GROUP_NOT_EXIST);
            } else {
                /*
                注意 这里删除分组以后，里面的设备也会被删除！
                 */
                deviceGroupRepository.delete(deviceGroup);
                List<Device> devices = deviceRepository.findAllByDeviceGroup(deviceGroup);//删除分组内部的设备
                deviceRepository.delete(devices);

                resultJson.put("state", 1);
                resultJson.put("message", SuccessMessageEnum.DEVICE_GROUP_DELETE_SUCCESS);
            }

        }

        return resultJson;
    }

    /**
     * 分组列表
     * 这里有一个流程：
     * 1 先加载 分组列表
     * |-------------|
     * |家庭组        |
     * |游客组        |
     * |-------------|
     * <p>
     * 2 再通过分组的serialNumber 来获取具体的分组内的设备
     * 展开{和QQ好友类似}:
     * |-------------|
     * |家庭组        |
     * |-------------|
     * |设备1|
     * |设备2|
     * |设备3|
     */
    @ApiOperation(value = "分组列表", notes = "分组列表", httpMethod = "GET")
    @RequestMapping(value = "/user/device/groups", method = RequestMethod.GET)
    public JSONObject groups() {
        AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JSONObject resultJson = new JSONObject();
        List<DeviceGroup> deviceGroupList = deviceGroupRepository.findAllByAppUser(appUser);

        if (deviceGroupList.size() > 0) {
            resultJson.put("state", 1);
            resultJson.put("data", deviceGroupList);
            resultJson.put("message", SuccessMessageEnum.OPERATE_SUCCESS);
        } else {
            resultJson.put("state", 0);
            resultJson.put("message", FailureMessageEnum.EMPTY_DATA_SET);

        }

        return resultJson;

    }


    /**
     * 设备列表:是一个分组下面的设备列表
     */
    @ApiOperation(value = "设备列表", notes = "设备列表", httpMethod = "GET")
    @RequestMapping(value = "/user/device/devices/{serialNumber}/{pageNumber}/{pageSize}", method = RequestMethod.GET)
    public JSONObject devices(@PathVariable String serialNumber, @PathVariable Integer pageNumber, @PathVariable Integer pageSize) {
        JSONObject resultJson = new JSONObject();
        if (pageNumber == null || pageSize == null) {
            resultJson.put("state", 0);
            resultJson.put("message", FailureMessageEnum.INVALID_PARAM);
        } else {
            try {
                DeviceGroup deviceGroup = deviceGroupRepository.findOne(serialNumber);
                if (deviceGroup != null) {

                    List<Device> devicePage = deviceRepository.findAllByDeviceGroup(deviceGroup);
                    if (devicePage != null) {
                        resultJson.put("state", 1);
                        resultJson.put("data", devicePage);
                        resultJson.put("message", SuccessMessageEnum.OPERATE_SUCCESS);
                    } else {
                        resultJson.put("state", 0);
                        resultJson.put("message", FailureMessageEnum.EMPTY_DATA_SET);
                    }
                } else {
                    resultJson.put("state", 0);
                    resultJson.put("message", "请求必须附带分组序列号!");
                }


            } catch (Exception e) {
                resultJson.put("state", 0);
                resultJson.put("message", FailureMessageEnum.INVALID_PARAM);
            }
        }


        return resultJson;

    }


    @ApiOperation(value = "设备列表", notes = "设备列表", httpMethod = "GET")
    @RequestMapping(value = "/user/device/devices/allDevice/{pageNumber}/{pageSize}", method = RequestMethod.GET)
    public JSONObject devices(@PathVariable Integer pageNumber, @PathVariable Integer pageSize) {

        AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ReturnResult.returnResultWithData(
                1, "查询成功",
                deviceRepository.findAllByAppUser(new PageRequest(pageNumber, pageSize), appUser));
    }
}
