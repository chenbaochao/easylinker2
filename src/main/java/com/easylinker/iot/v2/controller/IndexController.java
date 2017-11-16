package com.easylinker.iot.v2.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wwhai on 2017/11/15.
 */
@RestController
@RequestMapping("/")
public class IndexController {
    JSONObject jsonObject = new JSONObject();

    @RequestMapping("/test")
    public JSONObject test() {
        jsonObject.put("message", "Test成功");
        return jsonObject;
    }

}
