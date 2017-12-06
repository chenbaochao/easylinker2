package com.easylinker.iot.v2.plugin;

import com.alibaba.fastjson.JSONObject;
import com.squareup.okhttp.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by wwhai on 2017/11/27.
 * 又拍云短信配置
 * POST https://sms-api.upyun.com/api/messages
 * Authorization: <your token>
 * {
 * "template_id": 2,
 * "mobile": "15757,15757654321”
 * }
 */
@Component
public final class UpYunSmsSender {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    protected static OkHttpClient okHttpClient = new OkHttpClient();


    public static OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public static void setOkHttpClient(OkHttpClient okHttpClient) {
        UpYunSmsSender.okHttpClient = okHttpClient;
    }

    public JSONObject sendSms(String token, String vars, String templateId, String[] mobiles) {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("template_id", templateId);
        //"$var1|$var2"//这个表示发送的消息内容
        jsonObject.put("vars", vars);
        jsonObject.put("mobile", mobiles);


        JSONObject returnJson = null;
        try {
            RequestBody requestBody = RequestBody.create(JSON, jsonObject.toString());
            Request request = new Request
                    .Builder()
                    .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
                    .addHeader("Authorization", token)
                    .post(requestBody)
                    .url("https://sms-api.upyun.com/api/messages")
                    .build();


            Response response = getOkHttpClient().newCall(request).execute();
            returnJson = JSONObject.parseObject(response.body().string());
            response.body().close();


        } catch (IOException e) {
            returnJson = new JSONObject();
            jsonObject.put("state", 0);
            jsonObject.put("message", "Json Error!");
            return returnJson;

        }
        return returnJson;

    }

}



