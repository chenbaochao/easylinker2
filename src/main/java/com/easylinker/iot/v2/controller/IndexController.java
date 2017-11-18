package com.easylinker.iot.v2.controller;

import com.alibaba.fastjson.JSONObject;
import com.easylinker.iot.v2.annotation.EasyApi;
import com.easylinker.iot.v2.annotation.EasyLinkerApiEntry;
import com.easylinker.iot.v2.annotation.ProjectInformation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wwhai on 2017/11/15.
 */
@RestController
@RequestMapping("/")
@EasyLinkerApiEntry
@ProjectInformation(name = "EasyLinker物联网项目", startTime = "2017", endTime = "2019", charger = "小明", remarks = "项目描述")
public class IndexController {
    JSONObject jsonObject = new JSONObject();

    @EasyApi(name = "测试方法", router = "/test", param = {"参数1", "参数2"}, author = "王文海")
    @RequestMapping("/test")
    public JSONObject test() {
        jsonObject.put("message", "Test成功");
        return jsonObject;
    }

}
