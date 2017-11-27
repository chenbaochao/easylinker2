package com.easylinker.iot.v2.emq;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

/**
 * Created by wwhai on 2017/11/26.
 * 这个是EMQ的监控API提供类 注意 是Final的
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


    @Override
    public JSONObject publishMessage(JSONObject message) {
        return postEmqManagementInfo("mqtt/publish", message);
    }


    public static void main(String[] args) throws Exception {
        EMQApiProvider emqApiProvider = new EMQApiProvider();

        JSONObject json = new JSONObject();
        json.put("a", "s");
        json.put("b", 1);
        json.put("c", false);
        json.put("d", "C_1492145414740");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("topic", "$client/3e579fbe879116589e0753797b0e0bf5");
        jsonObject.put("payload", json.toJSONString());
        jsonObject.put("qos", 1);
        jsonObject.put("retain", false);
        jsonObject.put("client_id", "C_1492145414740");

        System.out.println(emqApiProvider.publishMessage(jsonObject));
    }
}
