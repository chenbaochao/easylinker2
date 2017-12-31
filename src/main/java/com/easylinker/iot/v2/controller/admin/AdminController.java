package com.easylinker.iot.v2.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.easylinker.iot.v2.repository.AppUserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wwhai on 2017/11/15.
 * 后天管理控制器
 */
@Api(value = "管理员的操作相关", description = "管理员的相关操作", tags = "管理员操作")
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AppUserRepository appUserRepository;

    @ApiOperation(value = "删除一个用户", notes = "删除一个用户", httpMethod = "DELETE")
    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public JSONObject deleteUser() {
        JSONObject resultJson = new JSONObject();

        return resultJson;
    }

    @ApiOperation(value = "删除一个用户", notes = "删除一个用户", httpMethod = "GET")
    @RequestMapping(value = "/users/{pageNumber}/{pageSize}", method = RequestMethod.GET)
    public JSONObject users(@PathVariable Integer pageNumber, @PathVariable Integer pageSize) {
        JSONObject resultJson = new JSONObject();


        return resultJson;
    }
}
