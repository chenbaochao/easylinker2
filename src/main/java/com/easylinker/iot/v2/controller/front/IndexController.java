package com.easylinker.iot.v2.controller.front;

import com.alibaba.fastjson.JSONObject;
import com.easylinker.iot.v2.model.AppUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wwhai on 2017/11/15.
 */
@RestController
@RequestMapping("/")
@Api(value = "网站首页控制", description = "首页控制")
public class IndexController {
    JSONObject jsonObject = new JSONObject();


    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ApiOperation(value = "测试API", notes = "这个是用来测试的", response = AppUser.class, httpMethod = "POST")
    @ApiResponses(value = {@ApiResponse(code = 405, message = "Invalid input", response = AppUser.class)})

    public JSONObject testPost() {
        jsonObject.put("message", "Test成功");
        return jsonObject;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ApiOperation(value = "测试API", notes = "这个是用来测试的", response = AppUser.class, httpMethod = "GET")
    @ApiResponses(value = {@ApiResponse(code = 405, message = "Invalid input", response = AppUser.class)})

    public JSONObject testGet() {
        jsonObject.put("message", "Test成功");
        return jsonObject;
    }


}
