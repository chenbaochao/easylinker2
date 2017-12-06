package com.easylinker.iot.v2.controller.user;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.easylinker.iot.v2.constants.FailureMessageEnum;
import com.easylinker.iot.v2.constants.SuccessMessageEnum;
import com.easylinker.iot.v2.model.user.AppUser;
import main.java.com.UpYun;
import main.java.com.upyun.FormUploader;
import main.java.com.upyun.Params;
import main.java.com.upyun.Result;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wwhai on 2017/12/7.
 */
@RestController
@RequestMapping("/file")
public class UpYunFileServiceController {
    FormUploader uploader = new FormUploader("easylinker", "lF+X+o/etZ1Xi7kkFdaXwtoZwrA=", null);

    @RequestMapping("/upload")
    public JSONObject upload(@RequestParam(name = "files") MultipartFile[] multipartFiles) throws FileUploadException, IOException {

        AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // 上传文件
        JSONArray resultArray = new JSONArray();
        JSONObject resultJson = new JSONObject();
        System.out.println(multipartFiles.length);
        for (MultipartFile multipartFile : multipartFiles) {
            File file = new File("/" + multipartFile.getOriginalFilename());
            multipartFile.transferTo(file);
            String savePath = "/{year}/{mon}/{day}/upload_{random32}{.suffix}";
            Map<String, Object> paramsMap = new HashMap<>();
            paramsMap.put(Params.SAVE_KEY, savePath);
            Result result = null;
            try {
                result = uploader.upload(paramsMap, file);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (result.isSucceed()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("state", 1);
                jsonObject.put("file", file.getName());
                jsonObject.put("message", SuccessMessageEnum.OPERATE_SUCCESS);
                resultArray.add(jsonObject);
            } else {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("state", 0);
                jsonObject.put("file", file.getName());
                jsonObject.put("message", FailureMessageEnum.OPERATE_FAILED);
                resultArray.add(jsonObject);
            }
            resultJson.put("state", 1);
            resultJson.put("message", SuccessMessageEnum.OPERATE_SUCCESS);
            resultJson.put("data", resultArray);
        }
        return resultJson;
    }

    public static void main(String[] args) throws Exception {
        UpYun upyun = new UpYun(
                "easylinker",
                "18059150204",
                "wwhlovejava123"
        );

        boolean result = upyun.writeFile("/path/to/file", new File("H:\\easyLinker-2\\win10-ui-master\\index.html"), true);
        System.out.println(result);


    }

}
