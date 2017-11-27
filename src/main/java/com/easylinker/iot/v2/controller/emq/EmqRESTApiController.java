package com.easylinker.iot.v2.controller.emq;

import com.alibaba.fastjson.JSONObject;
import com.easylinker.iot.v2.emq.EMQApiProvider;
import com.easylinker.iot.v2.repository.DeviceRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by wwhai on 2017/11/25.
 * 这个用来提供EMQ交互，管理设备等等
 */
@Api(value = "EMQ设备管理操作相关", description = "EMQ设备管理操作相关")
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

    @ApiOperation(value = "获取具体的一个节点上的设备列表", notes = "获取具体的一个节点上的设备列表", httpMethod = "GET")
    @RequestMapping(value = "/getClientsOnNode/{nodeName:.+}", method = RequestMethod.GET)
    public JSONObject getClientsOnNode(@PathVariable String nodeName) {
        return emqApiProvider.getClientsOnNode(nodeName);
    }

    @ApiOperation(value = "获取具体的一个节点上的设备列表", notes = "获取具体的一个节点上的设备列表", httpMethod = "GET")
    @RequestMapping(value = "/getClientsOnNode/{nodeName:.+}/{clientId}", method = RequestMethod.GET)

    public JSONObject getClientInformationOnNode(@PathVariable String nodeName, @PathVariable String clientId) {
        return emqApiProvider.getClientInformationOnNode(nodeName, clientId);
    }

    /**
     * {
     * "topic"    : "test",
     * "payload"  : "hello",
     * "qos"      : 1,
     * "retain"   : false,
     * "client_id": "C_1492145414740"
     * }
     *
     * @param messageMap
     * @return
     */
    @ApiOperation(value = "发布消息", notes = "发布消息", httpMethod = "POST")
    @RequestMapping(value = "/publish", method = RequestMethod.POST)

    public JSONObject publish(@RequestBody Map<String, Object> messageMap) {
        JSONObject resultJson = new JSONObject();
        /**
         *这里比较坑  一定要按照规定格式
         * HTTP传过来的是字符串   但是  Boolean 值需要转换
         * 还有 qos 也需要转换
         */
        String topic = messageMap.get("topic").toString();
        String payload = messageMap.get("payload").toString();
        Integer qos = Integer.parseInt(messageMap.get("qos").toString());
        Boolean retain = (Boolean) messageMap.get("retain");
        JSONObject messageJson = new JSONObject();
        messageJson.put("topic", topic);
        messageJson.put("payload", payload);
        messageJson.put("qos", qos);
        messageJson.put("retain", retain.booleanValue());
        messageJson.put("client_id", "http");

        if (Integer.parseInt(emqApiProvider.publishMessage(messageJson).get("code").toString()) != 0) {
            resultJson.put("state", 0);
            resultJson.put("message", "推送失败");
        } else {
            resultJson.put("state", 1);
            resultJson.put("message", "推送成功");
        }

        return resultJson;
    }


}
