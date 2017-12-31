package com.easylinker.iot.v2.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.easylinker.iot.v2.model.user.AppUser;
import com.easylinker.iot.v2.repository.AppUserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 账号激活
 * Created by wwhai on 2017/12/25.
 */
@Api(value = "激活账号", description = "用户账号激活", tags = "用户激活")
@RestController
public class UserActiveController {
    @Autowired
    AppUserRepository appUserRepository;

    @ApiOperation(value = "激活账号", notes = "激活账号", httpMethod = "POST")
    @RequestMapping(value = "/user/active", method = RequestMethod.POST)
    public JSONObject addUser(@RequestParam String userId) {
        JSONObject resultJson = new JSONObject();
        AppUser appUser = appUserRepository.findOne(userId);
        if (appUser != null) {
            appUser.setEnabled(true);
            appUserRepository.save(appUser);

            resultJson.put("state", 1);
            resultJson.put("message", "激活成功!");

        } else {
            resultJson.put("state", 0);
            resultJson.put("message", "用户不存在!");
        }
        return resultJson;

    }
}
