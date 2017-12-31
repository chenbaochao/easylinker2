package com.easylinker.iot.v2.configure.security.securityenum;

/**
 * Created by wwhai on 2017/11/18.
 */

/**
 * 这个是对SpringSecurity的默认的URL的一些封装
 */
public enum SecurityCommonUrl {
    DEFAULT_USER_OPERATE_PATH("注册入口", "/user/register"),
    DEFAULT_STATIC_PATH("默认静态资源的路径", "/static/**"),
    DEFAULT_AVATAR_PATH("默认头像的路径", "/avatar/**"),
    DEFAULT_TEST_PATH("默认测试接口", "/testLogin"),
    DEFAULT_INDEX_PATH("默认首页", "/index");

    public static final String[] SWAGGER_UI_MATCHER = new String[]{
            "/v2/api-docs",
            "/docs.html"
    };
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


}
