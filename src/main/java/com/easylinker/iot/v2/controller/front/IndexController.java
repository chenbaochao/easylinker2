package com.easylinker.iot.v2.controller.front;

import com.easylinker.iot.v2.model.user.AppUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by wwhai on 2017/11/15.
 */
@Controller

@Api(value = "网站首页控制", description = "首页控制", tags = "默认前端首页")
public class IndexController {


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ApiOperation(value = "系统自带的简单首页", notes = "系统自带的简单首页", response = AppUser.class, httpMethod = "GET")
    public void index(HttpServletResponse httpServletResponse) throws Exception {
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("text/html");
        httpServletResponse.getWriter().write("<h1>运行成功</h1><a href=\"http://ww.easylinker.xyz\">[社区-文档]</a>");
        httpServletResponse.getWriter().flush();

    }


}
