package com.easylinker.iot.v2.emq;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

/**
 * Created by wwhai on 2017/11/26.
 */
@Component
public final class EMQApiProvider extends ApiProvider {

    @Override
    public JSONObject getAllNodes() {

        return getEmqManagementInfo("management/nodes");
    }

    @Override
    public JSONObject getNodeInformation(String nodeName) {
        return getEmqManagementInfo("management/nodes/" + nodeName);
    }

    @Override

    public JSONObject getClientsOnNode(String nodeName) {
        return getEmqManagementInfo("nodes/" + nodeName + "/clients");
    }

    @Override
    public JSONObject getClientInformationOnNode(String nodeName, String clientId) {
        return getEmqManagementInfo("nodes/" + nodeName + "/clients/" + clientId);
    }

    /**
     * POST
     * api/v2/mqtt/publish
     * 创建订阅
     * {
     * "topic"    : "test",
     * "payload"  : "hello",
     * "qos"      : 1,
     * "retain"   : false,
     * "client_id": "C_1492145414740"
     * }
     */

    @Override
    public JSONObject publishMessage(JSONObject message) {
        return postEmqManagementInfo("api/v2/mqtt/publish", message);
    }


    public static void main(String[] args) throws Exception {
        EMQApiProvider emqApiProvider = new EMQApiProvider();
//        System.out.println(emqApiProvider.getAllNodes());
//        System.out.println(emqApiProvider.getNodeInformation("emq@127.0.0.1"));
//        System.out.println(emqApiProvider.getClientsOnNode("emq@127.0.0.1"));
//        System.out.println(emqApiProvider.getClientInformationOnNode("emq@127.0.0.1", "emqttd_121483149967914"));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("topic", "test");
        jsonObject.put("payload", "hello");
        jsonObject.put("qos", 1);
        jsonObject.put("retain", false);
        jsonObject.put("client_id", "C_1492145414740");

        System.out.println(emqApiProvider.publishMessage(jsonObject));
    }
}
