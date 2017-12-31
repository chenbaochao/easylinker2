package com.easylinker.iot.v2.controller.user;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.easylinker.iot.v2.constants.SuccessMessageEnum;
import com.easylinker.iot.v2.emq.EMQApiProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wwhai on 2017/12/7.
 * 用来处理消息推送
 */
@Api(value = "通过HTTP协议 消息推送到设备", description = "这个是用来给设备推送消息的HTTP接口", tags = "消息推送")
@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    EMQApiProvider emqApiProvider;

    /**
     * 在后台给指定设备推送消息
     * 一组设备
     * {
     * "deviceIdArray":[
     * "device/762fba216ece10e1131d3ba8f88cb294",
     * "device/762fba216ece10e1131d3ba8f88cb294"
     * ],
     * "qos"      : 1,
     * "retain"   : false,
     * "unit"     : "CV",
     * "message":"hello world"
     * }
     */
    @ApiOperation(value = "推送消息", notes = "给设备推送消息", httpMethod = "POST")
    @RequestMapping(value = "/pushMessage", method = RequestMethod.POST)
    public JSONObject pushMessage(@RequestBody JSONObject pushBody) {
        JSONObject resultJson = new JSONObject();
        JSONArray deviceIdArray = pushBody.getJSONArray("deviceIdArray");
        String message = pushBody.getString("message");
        Integer qos = pushBody.getInteger("qos");
        String unit = pushBody.getString("unit");
        Boolean retain = pushBody.getBoolean("retain");
        for (Object id : deviceIdArray) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("topic", id.toString());
            jsonObject.put("payload", message);
            jsonObject.put("qos", qos);
            jsonObject.put("retain", retain);
            jsonObject.put("client_id", "http");

            emqApiProvider.publishMessage(jsonObject);
        }
        resultJson.put("message", SuccessMessageEnum.OPERATE_SUCCESS);
        resultJson.put("state", 1);

        return resultJson;

    }

}
