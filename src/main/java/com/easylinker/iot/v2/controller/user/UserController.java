package com.easylinker.iot.v2.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.easylinker.iot.v2.constants.FailureMessageEnum;
import com.easylinker.iot.v2.constants.SuccessMessageEnum;
import com.easylinker.iot.v2.model.device.Device;
import com.easylinker.iot.v2.model.device.DeviceGroup;
import com.easylinker.iot.v2.model.user.AppUser;
import com.easylinker.iot.v2.repository.AppUserRepository;
import com.easylinker.iot.v2.repository.DeviceGroupRepository;
import com.easylinker.iot.v2.repository.DeviceRepository;
import com.easylinker.iot.v2.utils.QRCodeGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by wwhai on 2017/11/15.
 * 用户相关
 */
@Api(value = "用户操作相关", description = "用户的增删改查操作")
@RestController
public class UserController {
    String username;
    String password;
    String email;
    String phone;
    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    DeviceRepository deviceRepository;
    @Autowired
    DeviceGroupRepository deviceGroupRepository;


    @ApiOperation(value = "测试", notes = "测试", httpMethod = "GET")
    @RequestMapping(value = "/user/test", method = RequestMethod.GET)
    public JSONObject test() {
        JSONObject resultJson = new JSONObject();
        resultJson.put("state", "0");
        resultJson.put("message", "testOk");
        return resultJson;
    }

    /**
     * 包含了注册
     *
     * @param loginParamMap
     * @return
     */

    @ApiOperation(value = "增加一个用户", notes = "增加一个用户", httpMethod = "POST")
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public JSONObject addUser(@RequestBody(required = false) Map<String, String> loginParamMap) {
        JSONObject resultJson = new JSONObject();
        /**
         * Map 提取参数的时候可能会抛出异常，所以进行异常捕获
         */
        try {
            username = loginParamMap.get("username");
            password = loginParamMap.get("password");
            email = loginParamMap.get("email");
            phone = loginParamMap.get("phone");
            /**
             * 排除非空
             */
            if (username.equals("") || password.equals("") || email.equals("") || phone.equals("")) {
                resultJson.put("state", 0);
                resultJson.put("message", FailureMessageEnum.INVALID_PARAM);
            } else {
                /**
                 *  判断用户是否存在
                 */

                if (appUserRepository.findTop1ByUsernameOrEmailOrPhone(username, email, phone) != null) {
                    resultJson.put("state", 0);
                    resultJson.put("message", FailureMessageEnum.USER_EXIST);

                    /**
                     * 所有的非法条件过滤以后，进行增加用户
                     */
                } else {
                    AppUser appUser = new AppUser();
                    appUser.setUsername(username);
                    appUser.setPassword(password);
                    appUser.setPhone(phone);
                    appUser.setEmail(email);
                    appUserRepository.save(appUser);
                    resultJson.put("state", 1);
                    resultJson.put("data", appUser);
                    resultJson.put("message", SuccessMessageEnum.REGISTER_SUCCESS);
                }

            }
        } catch (Exception e) {
            /**
             * 抛出空指针的时候，就是参数没有传递
             */
            if (e instanceof NullPointerException) {
                resultJson.put("state", 0);
                resultJson.put("message", FailureMessageEnum.INVALID_PARAM);
            }
        }

        return resultJson;
    }


    @ApiOperation(value = "更新一个用户", notes = "更新一个用户", httpMethod = "PUT")
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public JSONObject updateUser(@RequestBody(required = false) Map<String, String> userParamMap) {
        JSONObject resultJson = new JSONObject();
        try {
            String userId = userParamMap.get("userId");
            username = userParamMap.get("username");
            email = userParamMap.get("email");
            phone = userParamMap.get("phone");
            /**
             * 排除非空
             */
            if (userId.equals("") ||
                    username.equals("") || email.equals("") || phone.equals("")) {
                resultJson.put("state", 0);
                resultJson.put("message", FailureMessageEnum.INVALID_PARAM);
            } else {
                AppUser appUser = appUserRepository.findOne(userId);
                if (appUser != null) {
                    appUser.setUsername(username);
                    appUser.setPhone(phone);
                    appUser.setEmail(email);
                    appUserRepository.save(appUser);
                    resultJson.put("state", 1);
                    resultJson.put("data", appUser);
                    resultJson.put("message", SuccessMessageEnum.OPERATE_SUCCESS);
                } else {
                    resultJson.put("state", 0);
                    resultJson.put("message", FailureMessageEnum.USER_NOT_EXIST);
                }

            }
        } catch (Exception e) {
            resultJson.put("state", 0);
            resultJson.put("message", FailureMessageEnum.INVALID_PARAM);
        }

        return resultJson;
    }

    @ApiOperation(value = "查找一个用户", notes = "查询一个用户", httpMethod = "GET")
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public JSONObject findUser(@PathVariable String userId) {
        JSONObject resultJson = new JSONObject();
        if (userId != null) {
            AppUser appUser = appUserRepository.findOne(userId);
            if (appUser != null) {
                resultJson.put("state", 1);
                resultJson.put("data", appUser);
                resultJson.put("message", SuccessMessageEnum.OPERATE_SUCCESS);
            }
        } else {
            resultJson.put("state", 0);
            resultJson.put("message", FailureMessageEnum.OPERATE_FAILED);
        }


        return resultJson;
    }

    /**
     * 设备操作相关
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

    /**
     * 设备组处理相关
     */

    /**
     * 增加分组
     */

    @ApiOperation(value = "增加分组", notes = "增加分组", httpMethod = "POST")
    @RequestMapping(value = "/user/device/group", method = RequestMethod.POST)
    public JSONObject addGroup(@RequestBody Map<String, String> groupNameParamMap) throws Exception {
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
                deviceGroupRepository.delete(deviceGroup);
                resultJson.put("state", 1);
                resultJson.put("message", SuccessMessageEnum.DEVICE_GROUP_DELETE_SUCCESS);
            }

        }

        return resultJson;
    }

    /**
     * 分组列表
     */
    @ApiOperation(value = "分组列表", notes = "分组列表", httpMethod = "GET")
    @RequestMapping(value = "/user/device/groups", method = RequestMethod.GET)
    public JSONObject groups() {
        JSONObject resultJson = new JSONObject();
        List<DeviceGroup> deviceGroupList = deviceGroupRepository.findAll();

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
     * 设备列表
     */
    @ApiOperation(value = "设备列表", notes = "设备列表", httpMethod = "GET")
    @RequestMapping(value = "/user/device/devices/{pageNumber}/{pageSize}", method = RequestMethod.GET)
    public JSONObject devices(@PathVariable Integer pageNumber, @PathVariable Integer pageSize) {
        JSONObject resultJson = new JSONObject();
        if (pageNumber == null || pageSize == null) {
            resultJson.put("state", 0);
            resultJson.put("message", FailureMessageEnum.INVALID_PARAM);
        } else {
            try {
                Page<Device> devicePage = deviceRepository.findAll(new PageRequest(pageNumber, pageSize, new Sort(Sort.Direction.DESC, "id")));
                if (devicePage != null) {
                    resultJson.put("state", 1);
                    resultJson.put("data", devicePage);
                    resultJson.put("message", SuccessMessageEnum.OPERATE_SUCCESS);
                } else {
                    resultJson.put("state", 0);
                    resultJson.put("message", FailureMessageEnum.EMPTY_DATA_SET);
                }

            } catch (Exception e) {
                resultJson.put("state", 0);
                resultJson.put("message", FailureMessageEnum.INVALID_PARAM);
            }
        }


        return resultJson;

    }

}
