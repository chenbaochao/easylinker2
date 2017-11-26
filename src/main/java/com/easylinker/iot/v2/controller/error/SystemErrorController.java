package com.easylinker.iot.v2.controller.error;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wwhai on 2017/11/26.
 * 默认的错误处理信息
 */
@RestController
@Api(value = "系统错误", description = "系统错误")

public class SystemErrorController {
    @ApiOperation(value = "系统错误", notes = "系统错误表", httpMethod = "GET")
    @RequestMapping(value = "/system/error", method = RequestMethod.GET)
    public JSONObject error() {
        JSONObject resultJson = new JSONObject();
        resultJson.put("state", 0);
        resultJson.put("message", "Internal error!");
        return resultJson;
    }
}
