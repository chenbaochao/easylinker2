package com.easylinker.iot.v2.controller.plugin;

import com.alibaba.fastjson.JSONObject;
import com.easylinker.iot.v2.constants.SuccessMessageEnum;
import com.easylinker.iot.v2.model.config.UpYunAccount;
import com.easylinker.iot.v2.repository.UpYunAccountRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wwhai on 2017/11/27.
 * 集成又拍云
 */
@Api(value = "又拍云的配置操作", description = "又拍云的配置操作", hidden = true, tags = "又拍云配置")

@RestController
@RequestMapping("/upyun")
public class UpYunConFigureController {
    @Autowired
    UpYunAccountRepository upYunAccountRepository;

    /**
     * 修改配置信息
     */
    @ApiOperation(value = "自定义配置又拍云", notes = "自定义配置又拍云", httpMethod = "POST")

    @RequestMapping("/configUpYun")
    public JSONObject configUpYun(@RequestBody JSONObject configJson) {
        JSONObject resultJson = new JSONObject();
        String bucketName = resultJson.getString("bucketName");
        String username = resultJson.getString("username");
        String password = resultJson.getString("password");
        String apiKey = resultJson.getString("apiKey");
        UpYunAccount upYunAccount = upYunAccountRepository.findTopById("EASY_LINKER");
        upYunAccount.setApiKey(apiKey);
        upYunAccount.setBucketName(bucketName);
        upYunAccount.setUsername(username);
        upYunAccount.setPassword(password);
        upYunAccountRepository.save(upYunAccount);
        resultJson.put("state", 1);
        resultJson.put("message", SuccessMessageEnum.OPERATE_SUCCESS);
        resultJson.put("data", upYunAccount);
        return resultJson;


    }
}
