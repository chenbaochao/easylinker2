package com.easylinker.iot.v2.emq;

import com.alibaba.fastjson.JSONObject;
import com.squareup.okhttp.OkHttpClient;

import java.net.URL;

/**
 * Created by wwhai on 2017/11/24.
 * 这个是EMQ的API封装
 */
public abstract class ApiProvider {

    protected OkHttpClient okHttpClient;

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public void setOkHttpClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    public ApiProvider() {
        okHttpClient = new OkHttpClient();

    }

    protected JSONObject sendHttpPost(URL url) {
        JSONObject resultJson = new JSONObject();
        return resultJson;

    }

    /**
     * 发送GET请求
     *
     * @param apiUrl
     * @return
     */

    public JSONObject getEmqManagementInfo(String apiUrl) {

        return EMQApiHttpSender.sendHttpGet(BaseAuthHeaderProvider.LOCAL_HOST_NODE + apiUrl);
    }

    /**
     * 发送POST JSON请求
     *
     * @param apiUrl
     * @param jsonObject
     * @return
     */
    public JSONObject postEmqManagementInfo(String apiUrl, JSONObject jsonObject) {

        return EMQApiHttpSender.sendHttpPostRequestBody(BaseAuthHeaderProvider.LOCAL_HOST_NODE + apiUrl, jsonObject);
    }


    /**
     * GET
     * api/v2/management/nodes
     * 获取全部节点的基本信息
     *
     * @return
     */
    public JSONObject getAllNodes() {
        JSONObject resultJson = new JSONObject();
        return resultJson;
    }

    /**
     * GET
     * api/v2/management/nodes/emq@127.0.0.1
     * 获取指定节点的基本信息
     */
    public JSONObject getNodeInformation(String nodeName) {
        JSONObject resultJson = new JSONObject();
        return resultJson;
    }

    /**
     * GET
     * api/v2/monitoring/nodes
     * 获取全部节点的监控数据
     */

    public JSONObject getAllNodesInformation() {
        JSONObject resultJson = new JSONObject();
        return resultJson;
    }

    /**
     * GET
     * api/v2/nodes/emq@127.0.0.1/clients
     * 获取指定节点的客户端连接列表
     */
    public JSONObject getClientsOnNode(String nodeName) {
        JSONObject resultJson = new JSONObject();
        return resultJson;
    }


    /**
     * GET
     * api/v2/nodes/emq@127.0.0.1/clients/{clientid}
     * 获取节点指定客户端连接的信息
     */
    public JSONObject getClientInformationOnNode(String nodeName, String clientId) {
        JSONObject resultJson = new JSONObject();
        return resultJson;
    }


    /**
     * GET
     * api/v2/clients/{clientid}
     * 获取集群内指定客户端的信息
     */
    public JSONObject getClientInformationOnGroup() {
        JSONObject resultJson = new JSONObject();
        return resultJson;
    }

    /**
     * GET
     * api/v2/nodes/emq@127.0.0.1/sessions
     * 获取指定节点的会话列表
     */
    public JSONObject getSessionsOnNode() {
        JSONObject resultJson = new JSONObject();
        return resultJson;
    }

    /**
     * GET
     * api/v2/nodes/emq@127.0.0.1/sessions/{clientid}
     * 获取节点上指定客户端的会话信息
     */

    public JSONObject getClientSessionOnNode() {
        JSONObject resultJson = new JSONObject();
        return resultJson;
    }


    /**
     * GET
     * api/v2/sessions/{clientid}
     * 获取集群内指定客户端的会话信息
     */

    public JSONObject getClientSessionOnGroup() {
        JSONObject resultJson = new JSONObject();
        return resultJson;
    }

    /**
     * GET
     * api/v2/nodes/emq@127.0.0.1/subscriptions
     * 获取某个节点上的订阅列表
     */

    public JSONObject getSubscriptionsOnNode() {
        JSONObject resultJson = new JSONObject();
        return resultJson;
    }

    /**
     * GET
     * api/v2/nodes/emq@127.0.0.1/subscriptions/{clientid}
     * 获取节点上指定客户端的订阅信息
     */
    public JSONObject getSubscriptionInfoOnNode() {
        JSONObject resultJson = new JSONObject();
        return resultJson;
    }

    /**
     * GET
     * api/v2/subscriptions/{clientid}
     * 获取集群内指定客户端的订阅信息
     */

    public JSONObject getSubscriptionInfoOnGroup() {
        JSONObject resultJson = new JSONObject();
        return resultJson;
    }

    /**
     * GET
     * api/v2/routes
     * 获取集群路由表
     */

    public JSONObject getGroupRoutes() {
        JSONObject resultJson = new JSONObject();
        return resultJson;
    }

    /**
     * GET
     * api/v2/routes/{topic}
     * 获取集群内指定主题的路由信息
     */
    public JSONObject getRouteInformationOnGroupWithTopic() {
        JSONObject resultJson = new JSONObject();
        return resultJson;
    }

    /**
     * POST
     * api/v2/mqtt/publish
     * 发布消息
     */

    public JSONObject publishMessage(JSONObject message) {
        JSONObject resultJson = new JSONObject();
        return resultJson;
    }

    /**
     * POST
     * api/v2/mqtt/subscribe
     * 创建订阅
     */
    public JSONObject subscribeTopic(String topic) {
        JSONObject resultJson = new JSONObject();
        return resultJson;
    }

    /**
     * POST
     * api/v2/mqtt/unsubscribe
     * 取消订阅
     */
    public JSONObject unsubscribeTopic() {
        JSONObject resultJson = new JSONObject();
        return resultJson;
    }

    /**
     * GET
     * api/v2/nodes/emq@127.0.0.1/plugins
     * 获取节点的插件列表
     */

    public JSONObject getPluginsOnNode() {
        JSONObject resultJson = new JSONObject();
        return resultJson;
    }

    /**
     * PUT
     * api/v2/nodes/emq@127.0.0.1/plugins/{name}
     * 开启/关闭节点的指定插件
     */

    public JSONObject switchPluginOnNode() {
        JSONObject resultJson = new JSONObject();
        return resultJson;
    }


    /**
     * GET
     * api/v2/monitoring/listeners
     * 获取集群的监听器列表
     */

    public JSONObject getListenersOnGroup() {
        JSONObject resultJson = new JSONObject();
        return resultJson;
    }

    /**
     * GET
     * api/v2/monitoring/listeners/emq@127.0.0.1
     * 获取指定节点的监听器列表
     */

    public JSONObject getListenersOnNode() {
        JSONObject resultJson = new JSONObject();
        return resultJson;
    }

    /**
     * GET
     * api/v2/monitoring/metrics
     * 获取全部节点的度量指标
     */

    public JSONObject getMetricsOnAllNode() {
        JSONObject resultJson = new JSONObject();
        return resultJson;
    }

    /**
     * GET
     * api/v2/monitoring/metrics/emq@127.0.0.1
     * 获取指定节点的度量指标
     */

    public JSONObject getMetricsOnNode() {
        JSONObject resultJson = new JSONObject();
        return resultJson;
    }

    /**
     * GET
     * api/v2/monitoring/stats
     * 获取全部节点的运行统计
     */

    public JSONObject getStatusOnAllNode() {
        JSONObject resultJson = new JSONObject();
        return resultJson;
    }


    /**
     * GET
     * api/v2/monitoring/stats/emq@127.0.0.1
     * 获取指定节点的运行统计
     */

    public JSONObject getStatusOnNode() {
        JSONObject resultJson = new JSONObject();
        return resultJson;
    }


}
