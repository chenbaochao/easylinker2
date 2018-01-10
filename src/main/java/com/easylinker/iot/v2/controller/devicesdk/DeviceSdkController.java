package com.easylinker.iot.v2.controller.devicesdk;

import com.alibaba.fastjson.JSONObject;
import com.easylinker.iot.v2.model.device.SdkType;
import com.easylinker.iot.v2.utils.ReturnResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wwhai on 2018/1/10.
 */
@Api(value = "SDK生成器", description = "设备SDK生成器", tags = "SDK生成器")
@RestController(value = "/sdk")
public class DeviceSdkController {
    @ApiOperation(nickname = "生成SDK", value = "生成SDK", notes = "生成SDK", httpMethod = "GET")
    @RequestMapping(value = "/getSdk/{sdkName}", method = RequestMethod.GET)
    public JSONObject getSdk(@PathVariable(name = "sdkName") SdkType sdkType) {

        /**
         * 根据传递的SDK参数  生成SDK模板
         */
        switch (sdkType) {
            case C:
                return ReturnResult.returnResult(1, "C");
            //break;
            case CPP:
                return ReturnResult.returnResult(1, "CPP");
            //break;
            case JAVA:
                return ReturnResult.returnResult(1, "JAVA");
            //break;
            case PYTHON:
                return ReturnResult.returnResult(1, "PYTHON");
            //break;
            default:
                return ReturnResult.returnResult(0, "No Suck Sdk!");
        }

    }
}
