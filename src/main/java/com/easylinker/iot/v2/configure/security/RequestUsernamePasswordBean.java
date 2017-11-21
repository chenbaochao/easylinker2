package com.easylinker.iot.v2.configure.security;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 把认证时 ，提交的JSON ，封装成了一个类
 * 默认包含了  用户名  密码  验证码
 */
public class RequestUsernamePasswordBean {
    public static final String USERNAME_PARAM = "username";
    public static final String PASSWORD_PARAM = "password";
    public static final String CHECK_CODE_PARAM = "check_code";

    private String username;
    private String password;

    public String getUserrname() {
        return username;
    }

    public void setUserrname(String userrname) {
        this.username = userrname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RequestUsernamePasswordBean(HttpServletRequest httpServletRequest) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(
                new BufferedReader(
                        new InputStreamReader(httpServletRequest.getInputStream())));
        if (httpServletRequest.getMethod().equals("POST")) {
            String tempLine = "";
            StringBuffer jsonStringBuffer = new StringBuffer();
            while ((tempLine = bufferedReader.readLine()) != null) {
                jsonStringBuffer.append(tempLine);
            }
            JSONObject jsonObject = JSONObject.parseObject(jsonStringBuffer.toString());
            String username = jsonObject.get(USERNAME_PARAM).toString();
            String password = jsonObject.get(PASSWORD_PARAM).toString();
            this.setUserrname(username);
            this.setPassword(password);

        } else {
            throw new Exception("Only POST method can be support!");
        }

    }
}
