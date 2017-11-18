package com.easylinker.iot.v2.configure.security;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wwhai on 2017/11/18.
 * 这个类是抽象出来用来管理基本路由配置信息的
 */
public class SecurityRouter {
    private List<String> webResourcesRouter;
    private List<String> httpSecurityRouter;

    public SecurityRouter() {
        webResourcesRouter = new ArrayList<>();
        httpSecurityRouter = new ArrayList<>();
    }

    public String[] getWebResourcesRouter() {
        String[] urls = new String[]{};
        return webResourcesRouter.toArray(urls);
    }

    public String[] getHttpSecurityRouter() {
        String[] urls = new String[]{};
        return httpSecurityRouter.toArray(urls);
    }


    public void addWebResourcesRouter(String path) {

        webResourcesRouter.add(path);
    }

    public void addHttpSecurityRouter(String path) {
        this.httpSecurityRouter.add(path);

    }

}
