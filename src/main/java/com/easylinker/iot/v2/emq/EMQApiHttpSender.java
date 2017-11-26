package com.easylinker.iot.v2.emq;

import com.alibaba.fastjson.JSONObject;
import com.easylinker.iot.v2.constants.FailureMessageEnum;
import com.squareup.okhttp.*;

import java.io.IOException;

/**
 * Created by wwhai on 2017/11/26.
 * 这个用来发送HTTp请求
 */
public class EMQApiHttpSender {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    protected static OkHttpClient okHttpClient = new OkHttpClient();

    public static OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }


    public static JSONObject sendHttpGet(String url) {


        JSONObject resultJson = new JSONObject();

        try {
            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
                    .addHeader("Authorization", BaseAuthHeaderProvider.getBaseAuthHeader())
                    .build();

            Response response = getOkHttpClient().newCall(request).execute();
            if (response.isSuccessful()) {
                return JSONObject.parseObject(response.body().string());
            } else {
                resultJson.put("state", 0);
                resultJson.put("message", "Unexpected code " + response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultJson.put("state", 0);
            resultJson.put("message", FailureMessageEnum.OPERATE_FAILED);
        }
        return resultJson;
    }


    public static JSONObject sendHttpPostRequestBody(String url, JSONObject jsonObject) {

        JSONObject returnJson = null;
        try {
            RequestBody requestBody = RequestBody.create(JSON, jsonObject.toString());
            Request request = new Request
                    .Builder()
                    .post(requestBody)
                    .url(url)
                    .build();
            System.out.println(url);
            System.out.println(jsonObject);

            Response response = getOkHttpClient().newCall(request).execute();
            returnJson = JSONObject.parseObject(response.body().string());
            response.body().close();
            System.out.println(returnJson);


        } catch (IOException e) {
            returnJson = new JSONObject();
            jsonObject.put("state", 0);
            jsonObject.put("message", "Json Error!");
            return returnJson;

        }
        return returnJson;

    }


}
