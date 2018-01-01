package com.easylinker.iot.v2.controller.emq;

import com.alibaba.fastjson.JSONObject;
import com.easylinker.iot.v2.constants.FailureMessageEnum;
import com.easylinker.iot.v2.constants.SuccessMessageEnum;
import com.easylinker.iot.v2.emq.EMQApiProvider;
import com.easylinker.iot.v2.model.device.Device;
import com.easylinker.iot.v2.model.device.DeviceData;
import com.easylinker.iot.v2.repository.DeviceDataRepository;
import com.easylinker.iot.v2.repository.DeviceRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wwhai on 2017/11/25.
 * 这个用来提供EMQ交互，管理设备等等
 */
@Api(value = "EMQ设备管理操作相关", description = "EMQ设备管理操作相关", tags = "EMQ 核心API")
@RestController
@RequestMapping("/emq")
/**
 * 目前先实现4个功能
 * 1.获取所有的节点列表
 * 2.获取具体的一个节点信息
 * 3.获取具体的一个节点上的设备列表
 * 4.获取具体的一个设备信息
 */
public class EmqRESTApiController {
    @Autowired
    EMQApiProvider emqApiProvider;
    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    DeviceDataRepository deviceDataRepository;

    @ApiOperation(value = "获取所有的节点列表", notes = "获取所有的节点列表", httpMethod = "GET")
    @RequestMapping(value = "/getAllNodes", method = RequestMethod.GET)
    public JSONObject getAllNodes() {
        return emqApiProvider.getAllNodes();
    }

    @ApiOperation(value = "获取具体的一个节点信息", notes = "获取具体的一个节点信息", httpMethod = "GET")
    @RequestMapping(value = "/getNodeInformation/{nodeName:.+}", method = RequestMethod.GET)
    //注意{nodeName:.+}
    // 这个为什么这么写? SpringMVC 默认会把"."号当成文件后缀处理
    // 这个正则表达式，为了防止把IP地址当成文件后缀
    public JSONObject getNodeInformation(@PathVariable String nodeName) {
        return emqApiProvider.getNodeInformation(nodeName);
    }

    @ApiOperation(value = "获取具体的一个节点上的所有设备列表", notes = "获取具体的一个节点上的设备列表", httpMethod = "GET")
    @RequestMapping(value = "/getClientsOnNode/{nodeName:.+}", method = RequestMethod.GET)
    public JSONObject getClientsOnNode(@PathVariable String nodeName) {
        return emqApiProvider.getClientsOnNode(nodeName);
    }

    @ApiOperation(value = "获取具体的一个节点上的具体设备", notes = "获取具体的一个节点上的设备", httpMethod = "GET")
    @RequestMapping(value = "/getClientInformationOnNode/{nodeName:.+}/{clientId}", method = RequestMethod.GET)

    public JSONObject getClientInformationOnNode(@PathVariable String nodeName, @PathVariable String clientId) {
        return emqApiProvider.getClientInformationOnNode(nodeName, clientId);
    }


    @ApiOperation(value = "获取设备的数据列表", notes = "获取设备的数据列表", httpMethod = "GET")
    @RequestMapping(value = "/getDeviceDataList/{deviceId}/{pageNumber}/{pageSize}", method = RequestMethod.GET)

    public JSONObject getDeviceDataList(@PathVariable String deviceId,
                                        @PathVariable Integer pageNumber,
                                        @PathVariable Integer pageSize) {
        JSONObject resultJson = new JSONObject();
        if (deviceId != null && pageNumber != null && pageSize != null) {
            Device device = deviceRepository.findOne(deviceId);
            if (device != null) {
                Page<DeviceData> deviceDataPage = deviceDataRepository.findAllByDevice(device,
                        new PageRequest(pageNumber, pageSize)
                );
                resultJson.put("state", 1);
                resultJson.put("data", deviceDataPage);
                resultJson.put("message", SuccessMessageEnum.OPERATE_SUCCESS);

            } else {
                resultJson.put("state", 0);
                resultJson.put("message", FailureMessageEnum.EMPTY_DATA_SET);
            }

        } else {
            resultJson.put("state", 0);
            resultJson.put("message", FailureMessageEnum.INVALID_PARAM);
        }

        return resultJson;
    }
}
