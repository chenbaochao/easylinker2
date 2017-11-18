package com.easylinker.iot.v2.configure.security.securityenum;

/**
 * Created by wwhai on 2017/11/18.
 */

/**
 * 这个是对SpringSecurity的默认的URL的一些封装
 */
public enum SecurityCommonUrl {
    DEFAULT_INDEX("默认首页", "/"),
    DEFAULT_LOGIN_PAGE("登录入口", "/user/login"),
    DEFAULT_LOGOUT_PAGE("注销入口", "/logOutPage"),
    DEFAULT_STATIC_PATH("默认静态资源的路径", "/static/**"),
    DEFAULT_TEST_PATH("默认测试接口", "/test"),
    DEFAULT_USERNAME_NAME("默认的登录用户名的名称", "loginParam"),
    DEFAULT_PASSWORD_NAME("默认的登录密码的名称", "password"),
    ;

    private String name;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    SecurityCommonUrl(String name, String url) {
        this.name = name;
        this.url = url;
    }

    @Override
    public String toString() {
        return this.getUrl().toString();
    }

//    public static void main(String[] args) {
//        System.out.println(SecurityCommonUrl.DEFAULT_INDEX.getUrl());
//    }
}
