package com.easylinker.iot.v2.controller.plugin;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wwhai on 2017/11/27.
 * 集成短信发送
 */
@Api(value = "发送短信API", description = "发送短信的HTTP接口")
@RestController
@RequestMapping("/sms")
public class SMSController {
}
