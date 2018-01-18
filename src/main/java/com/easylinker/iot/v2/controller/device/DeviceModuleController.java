package com.easylinker.iot.v2.controller.device;

import com.alibaba.fastjson.JSONObject;
import com.easylinker.iot.v2.constants.SuccessMessageEnum;
import com.easylinker.iot.v2.controller.device.ui.enums.ModuleTag;
import com.easylinker.iot.v2.repository.DeviceModuleFunctionUIRepository;
import com.easylinker.iot.v2.utils.ReturnResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wwhai on 2018/1/17.
 * 前端发来module信息 这里接受到了以后 判断tag 分析接下来应该给前端什么功能
 */
@Api(value = "根据TAG 判断返回功能", description = "根据TAG 判断返回功能", tags = "根据TAG 判断返回功能")
@RestController
public class DeviceModuleController {
    @Autowired
    DeviceModuleFunctionUIRepository deviceModuleFunctionUIRepository;


    @ApiOperation(value = "获取当前主界面模块列表", notes = "获取当前主界面模块列表", httpMethod = "GET")
    @RequestMapping(value = "/user/device/ui/module/allTag", method = RequestMethod.GET)
    public JSONObject getAllModuleTag() {
        return ReturnResult.returnResultWithData(
                1,
                SuccessMessageEnum.OPERATE_SUCCESS.getMessage(),
                deviceModuleFunctionUIRepository.findAll()
        );
    }


    @ApiOperation(value = "提交前端tag", notes = "提交前端tag", httpMethod = "POST")
    @RequestMapping(value = "/user/device/ui/module/tag/data", method = RequestMethod.POST)
    public JSONObject getDataByModuleTag(@PathVariable ModuleTag moduleTag) {
        switch (moduleTag) {
            case HUMIDITY:
                return ReturnResult.returnResult(1, moduleTag.toString());
            case PRESSURE:
                return ReturnResult.returnResult(1, moduleTag.toString());

            case TEMPERATURE:
                return ReturnResult.returnResult(1, moduleTag.toString());

            case VALUE:
                return ReturnResult.returnResult(1, moduleTag.toString());

            case FILE:
                return ReturnResult.returnResult(1, moduleTag.toString());
            case LOCATION:
                return ReturnResult.returnResult(1, moduleTag.toString());

            case STRING:
                return ReturnResult.returnResult(1, moduleTag.toString());

            case SWITCHER:
                return ReturnResult.returnResult(1, moduleTag.toString());

            default:
                return ReturnResult.returnResult(1, moduleTag.toString());

        }


    }
}
