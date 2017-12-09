package com.easylinker.iot.v2.controller.plugin;

import com.alibaba.fastjson.JSONObject;
import com.easylinker.iot.v2.constants.SuccessMessageEnum;
import com.easylinker.iot.v2.model.config.UpYunAccount;
import com.easylinker.iot.v2.repository.UpYunAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wwhai on 2017/11/27.
 * 集成又拍云
 */
@RestController
@RequestMapping("/upyun")
public class UpYunController {
    @Autowired
    UpYunAccountRepository upYunAccountRepository;
    UpYunAccount upYunAccount;

//    UpYunController() {
//
//        if (upYunAccountRepository.findTopById("EASY_LINKER") == null) {
//            System.out.println("开始配置默认的又拍云账户!");
//            upYunAccount = new UpYunAccount();
//            upYunAccount.setId("EASY_LINKER");
//            upYunAccount.setUsername("EASY_LINKER");
//            upYunAccount.setPassword("EASY_LINKER");
//            upYunAccount.setBucketName("EASY_LINKER");
//            upYunAccount.setApiKey("EASY_LINKER");
//            upYunAccountRepository.save(upYunAccount);
//            System.out.println("又拍云默认账户配置成功，生产环境需要换成自己的!");
//        }
//
//    }

    /**
     * 修改配置信息
     */
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
