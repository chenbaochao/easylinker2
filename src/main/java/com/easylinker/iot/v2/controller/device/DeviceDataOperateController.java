package com.easylinker.iot.v2.controller.device;

import com.alibaba.fastjson.JSONObject;
import com.easylinker.iot.v2.constants.SuccessMessageEnum;
import com.easylinker.iot.v2.model.device.Device;
import com.easylinker.iot.v2.model.device.DeviceData;
import com.easylinker.iot.v2.repository.DeviceDataRepository;
import com.easylinker.iot.v2.repository.DeviceRepository;
import com.easylinker.iot.v2.utils.ReturnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by wwhai on 2018/1/18.
 */
@RestController

public class DeviceDataOperateController {
    @Autowired
    DeviceDataRepository deviceDataRepository;

    @Autowired
    DeviceRepository deviceRepository;

    /**
     * 目前是先获取
     * 温度
     * 湿度
     * 压强
     * CO2浓度
     * PM25
     *
     * @param openId
     * @param pageNumber
     * @param pageSize
     * @return
     */

    @RequestMapping(value = "/user/device/data/{openId}/{pageNumber}/{pageSize}")
    public JSONObject deviceData(@PathVariable String openId,
                                 @PathVariable Integer pageNumber,
                                 Integer pageSize) {
        if (pageNumber == null || pageSize == null) {
            pageNumber = 1;
            pageSize = 5;
        }

        Device device = deviceRepository.findTopByOpenId(openId);
        if (device != null) {
            List<DeviceData> deviceDataList = deviceDataRepository.findAllByDevice(device, new PageRequest(pageNumber, pageSize)).getContent();
            return ReturnResult.returnResultWithData(1, SuccessMessageEnum.OPERATE_SUCCESS.toString(), deviceDataList);
        } else {
            return ReturnResult.returnResult(0, "设备不存在");
        }

    }
}
