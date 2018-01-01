package com.easylinker.iot.v2.emq;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

/**
 * Created by wwhai on 2017/11/26.
 * 这个用来EMQ的  BaseHttp认证头生成
 * 默认账号  密码
 */
@Component
public class BaseAuthHeaderProvider {
    /**
     * easylinker.emq.api.host=http://47.94.173.41:8080/api/v2/
     * easylinker.emq.api.user=admin
     * easylinker.emq.api.password=public
     */

    @Value("${easylinker.emq.api.host}")
    public String API_HOST_NODE;
    @Value("${easylinker.emq.api.user}")
    public String BASE_USER;
    @Value("${easylinker.emq.api.password}")
    public String BASE_USER_PASSWORD;


    public String getAPI_HOST_NODE() {
        return API_HOST_NODE;
    }

    public void setAPI_HOST_NODE(String API_HOST_NODE) {
        this.API_HOST_NODE = API_HOST_NODE;
    }

    public String getBASE_USER() {
        return BASE_USER;
    }

    public void setBASE_USER(String BASE_USER) {
        this.BASE_USER = BASE_USER;
    }

    public String getBASE_USER_PASSWORD() {
        return BASE_USER_PASSWORD;
    }

    public void setBASE_USER_PASSWORD(String BASE_USER_PASSWORD) {
        this.BASE_USER_PASSWORD = BASE_USER_PASSWORD;
    }

    public String getBaseAuthHeader() {
        String auth = BASE_USER + ":" + BASE_USER_PASSWORD;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuth);
        return authHeader;
    }
}
