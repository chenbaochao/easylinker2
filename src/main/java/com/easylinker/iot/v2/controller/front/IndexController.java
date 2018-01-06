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
        httpServletResponse.getWriter().write("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "  <meta http-equiv=\"Access-Control-Allow-Origin\" content=\"*\">\n" +
                "  <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
                "  <title>物联网控制中心</title>\n" +
                "  <script src=\"https://unpkg.com/axios/dist/axios.min.js\"></script>\n" +
                "\n" +
                "  <style lang=\"\">\n" +
                "    * {\n" +
                "      margin: 0;\n" +
                "      padding: 0;\n" +
                "      font-size: 18px;\n" +
                "      background: #000;\n" +
                "    }\n" +
                "    \n" +
                "    .border {\n" +
                "      height: 15px;\n" +
                "      background: #121314;\n" +
                "    }\n" +
                "    \n" +
                "    .header {\n" +
                "      height: 50px;\n" +
                "      line-height: 50px;\n" +
                "      text-align: center;\n" +
                "      color: #ECECEC;\n" +
                "    }\n" +
                "    \n" +
                "    #led {\n" +
                "      position: fixed;\n" +
                "      top: 50%;\n" +
                "      left: 50%;\n" +
                "      width: 250px;\n" +
                "      height: 250px;\n" +
                "      border-radius: 50%;\n" +
                "      transform: translate3d(-50%, -50%, 0);\n" +
                "      transition: all 0.5s;\n" +
                "    }\n" +
                "    \n" +
                "    .off {\n" +
                "      background: rgba(255, 255, 255, 1);\n" +
                "      box-shadow: 0px 0px 10px rgba(255, 255, 255, 1), 0px 0px 30px rgba(255, 255, 255, 1), 0px 0px 50px rgba(255, 255, 255, 1);\n" +
                "    }\n" +
                "    \n" +
                "    .on {\n" +
                "      background: rgba(255, 75, 75, 1);\n" +
                "      box-shadow: 0px 0px 10px rgba(255, 75, 75, 1), 0px 0px 20px rgba(255, 75, 75, 1), 0px 0px 30px rgba(255, 75, 75, 1);\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "  <div class=\"wrapper\">\n" +
                "    <div class=\"header\">\n" +
                "      <h1>物联网控制中心</h1>\n" +
                "    </div>\n" +
                "    <div class=\"border\"></div>\n" +
                "    <div class=\"content\">\n" +
                "      <div id=\"led\" class=\"switch on\">\n" +
                "\n" +
                "      </div>\n" +
                "    </div>\n" +
                "  </div>\n" +
                "  <script>\n" +
                "    axios.post('/userLogin', {\n" +
                "        \"loginParam\": \"wwhai\",\n" +
                "        \"password\": \"wwhai\"\n" +
                "      })\n" +
                "      .then(function(response) {\n" +
                "        console.log(response)\n" +
                "      })\n" +
                "      .catch(function(error) {\n" +
                "        console.error(response)\n" +
                "      });\n" +
                "    let flag = 0;\n" +
                "    //document.cookie = 'JSESSION=8C091CE3852FC4E5C557FF3A793364E7'\n" +
                "    document.getElementById('led').addEventListener('click', function() {\n" +
                "      flag = flag === 1 ? 0 : 1;\n" +
                "      this.setAttribute('class', flag ? 'off' : 'on')\n" +
                "      axios.post('/message/pushMessage', {\n" +
                "          \"deviceIdArray\": [\n" +
                "            \"device/8305132bc868fa613dfa2fa3eac6053a\"\n" +
                "          ],\n" +
                "          \"qos\": 0,\n" +
                "          \"retain\": false,\n" +
                "          \"message\": flag + ''\n" +
                "        })\n" +
                "        .then(function(response) {\n" +
                "          console.log(response)\n" +
                "        })\n" +
                "        .catch(function(error) {\n" +
                "          console.error(response)\n" +
                "        });\n" +
                "\n" +
                "    })\n" +
                "  </script>\n" +
                "</body>\n" +
                "\n" +
                "</html>");
        httpServletResponse.getWriter().flush();

    }


}
