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
    DEFAULT_TEST_PATH("默认测试接口", "/test"),
    DEFAULT_INDEX_PATH("默认首页", "/index"),
    DEFAULT_DOCUMENT_PATH("默认文档页面", "/document"),
    DEFAULT_PDF_URL("默认的PDF输出目录", "/api/pdf");

    public static final String[] SWAGGER_UI_MATCHER = new String[]{
            "/v2/api-docs",
            "/swagger-resources/configuration/ui",
            "/swagger-resources",
            "/swagger-resources/configuration/security",
            "/configuration/ui",
            "/swagger-resources",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"
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
