package com.easylinker.iot.v2.controller;

import com.alibaba.fastjson.JSONObject;
import com.easylinker.iot.v2.constants.SuccessMessageEnum;
import com.easylinker.iot.v2.model.user.AppUser;
import com.easylinker.iot.v2.repository.DeviceRepository;
import com.easylinker.iot.v2.utils.ReturnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wwhai on 2018/1/18.
 * 当前状态
 * 1 总共的设备
 * 2 在线的
 * 3 离线的
 * 4 服务器状态
 */
@RestController

public class SystemStateController {
    @Autowired
    DeviceRepository deviceRepository;

    @RequestMapping(value = "/user/systemState")
    public JSONObject systemState() {
        JSONObject jsonObject = new JSONObject();
        AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer totalDevice = deviceRepository.countAllByAppUser(appUser);
        Integer onLineCount = deviceRepository.countOnlineByAppUser(appUser);
        Integer offLineCount = deviceRepository.countOfflineByAppUser(appUser);
        jsonObject.put("totalDevice", totalDevice);
        jsonObject.put("onLineCount", onLineCount);
        jsonObject.put("offLineCount", offLineCount);
        return ReturnResult.returnResultWithData(1, SuccessMessageEnum.OPERATE_SUCCESS.toString(), jsonObject);


    }
}
