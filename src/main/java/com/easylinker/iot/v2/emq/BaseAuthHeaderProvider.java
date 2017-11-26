package com.easylinker.iot.v2.emq;

import org.apache.tomcat.util.codec.binary.Base64;

import java.nio.charset.Charset;

/**
 * Created by wwhai on 2017/11/26.
 * 这个用来EMQ的  BaseHttp认证头生成
 * 默认账号  密码
 */
public class BaseAuthHeaderProvider {

    public static final String LOCAL_HOST_NODE = "http://localhost:8080/api/v2/";
    public static final String BASE_USER = "admin";
    public static final String BASE_USER_PASSWORD = "public";

    public static String getBaseAuthHeader() {
        String auth = BASE_USER + ":" + BASE_USER_PASSWORD;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuth);
        return authHeader;
    }
}
