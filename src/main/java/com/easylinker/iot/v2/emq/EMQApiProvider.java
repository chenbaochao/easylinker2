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


}
