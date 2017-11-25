package com.easylinker.iot.v2.controller.blog;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wwhai on 2017/11/25.
 * 博客同步更新控制
 */
@Api(value = "博客的操作相关", description = "博客的相关操作")
@RestController
@RequestMapping("/blog")
public class BlogController {


    public JSONObject addBlog() {

        JSONObject resultJson = new JSONObject();
        return resultJson;

    }

    public JSONObject deleteBlog() {
        JSONObject resultJson = new JSONObject();
        return resultJson;


    }

    public JSONObject updateBlog() {
        JSONObject resultJson = new JSONObject();
        return resultJson;


    }

    public JSONObject findBlog() {
        JSONObject resultJson = new JSONObject();
        return resultJson;


    }
}
