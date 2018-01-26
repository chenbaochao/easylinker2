package com.easylinker.iot.v2.controller.device;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.easylinker.iot.v2.constants.SuccessMessageEnum;
import com.easylinker.iot.v2.controller.device.ui.enums.ModuleTag;
import com.easylinker.iot.v2.model.device.Device;
import com.easylinker.iot.v2.model.device.DeviceData;
import com.easylinker.iot.v2.repository.DeviceDataRepository;
import com.easylinker.iot.v2.repository.DeviceModuleFunctionUIRepository;
import com.easylinker.iot.v2.repository.DeviceRepository;
import com.easylinker.iot.v2.utils.ReturnResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wwhai on 2018/1/17.
 * 前端发来module信息 这里接受到了以后 判断tag 分析接下来应该给前端什么功能
 */
@Api(value = "根据TAG 判断返回功能", description = "根据TAG 判断返回功能", tags = "根据TAG 判断返回功能")
@RestController
public class DeviceModuleController {
    Logger logger = LoggerFactory.getLogger(DeviceModuleController.class);
    @Autowired
    DeviceModuleFunctionUIRepository deviceModuleFunctionUIRepository;
    @Autowired
    DeviceDataRepository deviceDataRepository;

    @Autowired
    DeviceRepository deviceRepository;

    @ApiOperation(value = "获取当前主界面模块列表", notes = "获取当前主界面模块列表", httpMethod = "GET")
    @RequestMapping(value = "/user/device/ui/module/all", method = RequestMethod.GET)
    public JSONObject getAllModule() {
        return ReturnResult.returnResultWithData(
                1,
                SuccessMessageEnum.OPERATE_SUCCESS.getMessage(),
                deviceModuleFunctionUIRepository.findAll()
        );
    }


    @ApiOperation(value = "提交前端tag", notes = "提交前端功能列表的tag", httpMethod = "GET")
    @RequestMapping(value = "/user/device/data/{openId}/{moduleTag}/{pageNumber}/{pageSize}", method = RequestMethod.GET)
    public JSONObject getDataByModuleTag(
            @PathVariable String openId,
            @PathVariable ModuleTag moduleTag,
            @PathVariable Integer pageNumber,
            @PathVariable Integer pageSize) {
        Device device = deviceRepository.findTopByOpenId(openId);
        if (device != null) {
            List<DeviceData> deviceDataList = deviceDataRepository.findAllByDevice(
                    device,
                    new PageRequest(pageNumber, pageSize)).getContent();

            switch (moduleTag) {
                case HUMIDITY:
                    return ReturnResult.returnResultWithData(1, "查询成功", getDataListByTag(ModuleTag.HUMIDITY, deviceDataList));
                case PRESSURE:
                    return ReturnResult.returnResult(1, moduleTag.toString());

                case TEMPERATURE:
                    return ReturnResult.returnResultWithData(1, "查询成功", getDataListByTag(ModuleTag.TEMPERATURE, deviceDataList));

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
        } else {
            return ReturnResult.returnResult(0, "设备不存在");
        }
    }


    /**
     * @param moduleTag
     * @param deviceDataList
     * @return {
     * "id": "51b1e883-58ea-4450-b4f6-c4292cf2da4f",
     * "isDelete": 0,
     * "createTime": 1516189736000,
     * "data": "{\"temperature\":18,\"humidity\":67}",
     * "unit": "C"
     * },
     */
    public List<Object> getDataListByTag(ModuleTag moduleTag, List<DeviceData> deviceDataList) {
        List<Object> dataList = new ArrayList<>();
        try {
            for (DeviceData deviceData : deviceDataList) {
                JSONObject dataJson = JSONObject.parseObject(deviceData.getData());
                Float data = dataJson.getFloat(moduleTag.toString());
                Date createTime = deviceData.getCreateTime();
                dataList.add(createTime);
                dataList.add(data);
            }
        } catch (Exception e) {
            logger.error("解析Tag的时候出错了");
        }
        return dataList;
    }

    @ApiOperation(value = "获取板子提交的数据", notes = "获取板子提交的数据", httpMethod = "GET")

    @RequestMapping(value = "/user/sensor/board/data/{openId}/{begin}/{end}/{pageNumber}/{pageSize}", method = RequestMethod.GET)
    private JSONObject getSensorBoardData(
            @PathVariable String openId,
            @PathVariable Long begin,
            @PathVariable Long end,
            @PathVariable Integer pageNumber,
            @PathVariable Integer pageSize) {
        JSONObject returnJson = new JSONObject();

        Device device = deviceRepository.findTopByOpenId(openId);
        if (device != null) {
            List<DeviceData> deviceDataList = deviceDataRepository.findAllByDeviceAndCreateTimeBetween(
                    device,
                    new Date(begin),
                    new Date(end),
                    new PageRequest(pageNumber, pageSize)).getContent();
            /**
             *
             HUMIDITY:
             PRESSURE:
             TEMPERATURE:
             VALUE:
             FILE:
             LOCATION:
             STRING:
             SWITCHER:
             */
            JSONArray dateArray = new JSONArray();
            JSONArray HUMIDITY_arrays = new JSONArray();
            JSONArray PRESSURE_arrays = new JSONArray();
            JSONArray TEMPERATURE_arrays = new JSONArray();
            JSONArray VALUE_arrays = new JSONArray();
            JSONArray FILE_arrays = new JSONArray();
            JSONArray LOCATION_arrays = new JSONArray();
            JSONArray STRING_arrays = new JSONArray();
            JSONArray SWITCHER_arrays = new JSONArray();


            for (DeviceData deviceData : deviceDataList) {
                JSONObject dataJson = JSONObject.parseObject(deviceData.getData());
                dateArray.add(deviceData.getCreateTime());
                HUMIDITY_arrays.add(dataJson.getString("HUMIDITY"));
                TEMPERATURE_arrays.add(dataJson.getString("TEMPERATURE"));
                PRESSURE_arrays.add(dataJson.getString("PRESSURE"));
                VALUE_arrays.add(dataJson.getString("VALUE"));
                FILE_arrays.add(dataJson.getString("FILE"));
                LOCATION_arrays.add(dataJson.getString("LOCATION"));
                STRING_arrays.add(dataJson.getString("STRING"));
                SWITCHER_arrays.add(dataJson.getString("SWITCHER"));

            }
            returnJson.put("date", dateArray);
            returnJson.put("HUMIDITY", HUMIDITY_arrays);
            returnJson.put("TEMPERATURE", TEMPERATURE_arrays);
            returnJson.put("PRESSURE", PRESSURE_arrays);
            returnJson.put("TEMPERATURE", TEMPERATURE_arrays);
            returnJson.put("VALUE", VALUE_arrays);
            returnJson.put("FILE", FILE_arrays);
            returnJson.put("LOCATION", LOCATION_arrays);
            returnJson.put("STRING", STRING_arrays);
            returnJson.put("SWITCHER", SWITCHER_arrays);
        }

        return ReturnResult.returnResultWithData(1, SuccessMessageEnum.OPERATE_SUCCESS.toString(), returnJson);

    }

}
