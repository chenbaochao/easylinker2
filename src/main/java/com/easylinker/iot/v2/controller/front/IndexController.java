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

@Api(value = "网站首页控制", description = "首页控制")
public class IndexController {


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ApiOperation(value = "系统自带的简单首页", notes = "系统自带的简单首页", response = AppUser.class, httpMethod = "GET")
    public void index(HttpServletResponse httpServletResponse) throws Exception {
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("text/html");
        httpServletResponse.getWriter().write(
                "<div align=\"center\"><hr>" +
                        "<h1>EasyLinker</h1>\n" +
                        "<h2>Version2.0</h2>" +
                        "<p>By EasyTeam.2017.10</p>\n" +
                        "<a href=\"swagger-ui.html\">【API文档】</a><br>" +
                        "<a href=\"http://www.easylinker.xyz\">【我们的网站】</a><hr>" +
                        "<p style=\"color:blue;\" >如果你看到这个页面，说明项目启动成功了!本页面仅仅是一个测试页面，如果你要自定义，请修改源代码里面的具体逻辑!</p>"
                        + "</div>"
        );
        httpServletResponse.getWriter().flush();

    }


}
