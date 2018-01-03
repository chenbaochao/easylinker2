package com.easylinker.iot.v2.controller.emq;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.easylinker.iot.v2.constants.SuccessMessageEnum;
import com.easylinker.iot.v2.emq.EMQApiProvider;
import com.easylinker.iot.v2.model.device.Device;
import com.easylinker.iot.v2.model.device.DeviceData;
import com.easylinker.iot.v2.repository.DeviceDataRepository;
import com.easylinker.iot.v2.repository.DeviceRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
    @Autowired
    DeviceRepository deviceRepository;
    @Autowired
    DeviceDataRepository deviceDataRepository;

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


    /**
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
        String unit = messageMap.get("unit").toString();
        String topic = messageMap.get("topic").toString();
        String payload = messageMap.get("payload").toString();
        Integer qos = Integer.parseInt(messageMap.get("qos").toString());
        Boolean retain = (Boolean) messageMap.get("retain");
        JSONObject messageJson = new JSONObject();

        messageJson.put("unit", unit);
        messageJson.put("topic", topic);
        messageJson.put("payload", payload);
        messageJson.put("qos", qos);
        messageJson.put("retain", retain.booleanValue());
        messageJson.put("client_id", "http");

        if (retain) {//判断是否持久化
            Device device = deviceRepository.findTopByOpenId(topic.split("/")[1].toString());
            if (device != null) {
                DeviceData deviceData = new DeviceData();
                deviceData.setData(payload);
                deviceData.setDevice(device);
                deviceData.setUnit(unit);
                deviceDataRepository.save(deviceData);
            }

        }


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
