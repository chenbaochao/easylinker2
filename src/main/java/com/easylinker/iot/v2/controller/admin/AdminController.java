package com.easylinker.iot.v2.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.easylinker.iot.v2.constants.FailureMessageEnum;
import com.easylinker.iot.v2.constants.SuccessMessageEnum;
import com.easylinker.iot.v2.model.user.AppUser;
import com.easylinker.iot.v2.repository.AppUserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public JSONObject deleteUser(@RequestBody JSONObject body) {
        JSONObject resultJson = new JSONObject();

        return resultJson;
    }

    @ApiOperation(value = "删除一个用户", notes = "删除一个用户", httpMethod = "GET")
    @RequestMapping(value = "/users/{pageNumber}/{pageSize}", method = RequestMethod.GET)
    public JSONObject users(@PathVariable Integer pageNumber, @PathVariable Integer pageSize) {
        JSONObject resultJson = new JSONObject();


        return resultJson;
    }

    /**
     * 查找把一个用户
     *
     * @param param
     * @return
     */

    @ApiOperation(value = "查找一个用户", notes = "查询一个用户", httpMethod = "GET")
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public JSONObject findUser(@PathVariable String param) {
        JSONObject resultJson = new JSONObject();
        if (param != null) {
            AppUser appUser = appUserRepository.findTop1ByUsernameOrEmailOrPhone(param, param, param);
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
}
