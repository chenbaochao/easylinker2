package com.easylinker.iot.v2.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.easylinker.iot.v2.constants.FailureMessageEnum;
import com.easylinker.iot.v2.constants.SuccessMessageEnum;
import com.easylinker.iot.v2.model.device.DeviceGroup;
import com.easylinker.iot.v2.model.user.AppUser;
import com.easylinker.iot.v2.repository.AppUserRepository;
import com.easylinker.iot.v2.repository.DeviceGroupRepository;
import com.easylinker.iot.v2.utils.MD5Generator;
import com.easylinker.iot.v2.utils.ReturnResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.Map;

/**
 * Created by wwhai on 2017/11/15.
 * 用户相关
 */
@Api(value = "用户操作相关", description = "用户的增删改查操作", tags = "用户操作")
@RestController
public class UserController {
    String username;
    String password;
    String email;
    String phone;
    @Autowired
    AppUserRepository appUserRepository;


    @Autowired
    DeviceGroupRepository deviceGroupRepository;


    @ApiOperation(nickname = "测试登录", value = "测试登录", notes = "测试登录", httpMethod = "POST")
    @RequestMapping(value = "/testLogin", method = RequestMethod.POST)
    public JSONObject testUserLogin() {
        return ReturnResult.returnResultWithData(1, "这个仅仅是用来测试登录的，系统的登录地址是:/userLogin", AppUser.class);
    }

    /**
     * 注册
     */

    @ApiOperation(value = "增加一个用户", notes = "增加一个用户", httpMethod = "POST")
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    @Transactional
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
                    resultJson.put("message", FailureMessageEnum.USER_ALREADY_EXIST);

                    /**
                     * 所有的非法条件过滤以后，进行增加用户
                     */
                } else {
                    AppUser appUser = new AppUser();
                    appUser.setUsername(username);
                    appUser.setPassword(MD5Generator.EncodingMD5(password));
                    appUser.setPhone(phone);
                    appUser.setEmail(email);

                    DeviceGroup deviceGroup = new DeviceGroup();
                    deviceGroup.setName("DEFAULT_GROUP");
                    deviceGroup.setAppUser(appUser);

//                    try {
//                        /**
//                         * 这里先不写  因为QQ邮箱出问题了
//                         */
//                        //sendEmail(email);//发送邮件
//                        //emailSender.sendEmail(email);
//                    } catch (Exception e) {
//                        resultJson.put("state", 0);
//                        resultJson.put("message", "系统内部错误!");
//                        return resultJson;
//                    }
                    appUserRepository.save(appUser);
                    deviceGroupRepository.save(deviceGroup);
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
                e.printStackTrace();
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


}
