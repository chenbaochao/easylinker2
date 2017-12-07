package com.easylinker.iot.v2.controller.user;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.easylinker.iot.v2.constants.FailureMessageEnum;
import com.easylinker.iot.v2.constants.SuccessMessageEnum;
import com.easylinker.iot.v2.model.user.AppUser;
import com.easylinker.iot.v2.utils.FilePathHelper;
import main.java.com.UpYun;
import main.java.com.upyun.FormUploader;
import main.java.com.upyun.Params;
import main.java.com.upyun.Result;
import org.aspectj.util.FileUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wwhai on 2017/12/7.
 */
@RestController
@RequestMapping("/file")
public class UpYunFileServiceController {
    public static final String SAVE_URL = "/easylinker/user/";
    /**
     * 后面从数据库里面加载，暂时写死
     */
    FormUploader uploader = new FormUploader(
            "easylinker",
            "lF+X+o/etZ1Xi7kkFdaXwtoZwrA=",
            null);
    UpYun upyun = new UpYun(
            "easylinker",
            "18059150204",
            "wwhlovejava123"
    );

    @RequestMapping("/upload")
    public JSONObject upload(@RequestParam(name = "files") MultipartFile[] multipartFiles) throws Exception {

        AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JSONArray resultArray = new JSONArray();
        JSONObject resultJson = new JSONObject();
        for (MultipartFile multipartFile : multipartFiles) {
            File file = new File("/uploadFiles/" + multipartFile.getOriginalFilename());
            FilePathHelper.dirExists(file);
            File tempFile = new File(file.getAbsolutePath());
            try {
                multipartFile.transferTo(tempFile);
            } catch (Exception e) {
                resultJson.put("state", 1);
                resultJson.put("message", FailureMessageEnum.OPERATE_FAILED);
                return resultJson;
            }

            String savePath = SAVE_URL + appUser.getId() + "/" + file.getName();
            Map<String, Object> paramsMap = new HashMap<>();
            paramsMap.put(Params.SAVE_KEY, savePath);
            Result result = uploader.upload(paramsMap, file);
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
            FileUtil.deleteContents(file);

        }
        resultJson.put("state", 1);
        resultJson.put("message", SuccessMessageEnum.OPERATE_SUCCESS);
        resultJson.put("data", resultArray);
        return resultJson;
    }

    @RequestMapping(value = "/deleteFiles", method = RequestMethod.POST)
    public JSONObject deleteFiles(@RequestBody JSONObject filesJson) {
        AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JSONObject resultJson = new JSONObject();
        JSONObject deleteResultJson = new JSONObject();

        JSONArray jsonArray = filesJson.getJSONArray("files");
        List<UpYun.FolderItem> items = upyun.readDir(SAVE_URL + appUser.getId() + "/");
        for (Object object : jsonArray) {

            boolean isDelete = false;
            try {
                isDelete = upyun.deleteFile(SAVE_URL + appUser.getId() + "/" + object.toString());
            } catch (Exception e) {

            }
            if (isDelete) {
                deleteResultJson.put(object.toString(), true);

            } else {
                deleteResultJson.put(object.toString(), false);
            }

        }
        jsonArray.add(deleteResultJson);
        resultJson.put("state", 1);
        resultJson.put("message", SuccessMessageEnum.OPERATE_SUCCESS);
        resultJson.put("data", deleteResultJson);
        return resultJson;

    }

    @RequestMapping("/files")
    public JSONObject files() {
        AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JSONObject resultJson = new JSONObject();
        JSONArray resultArray = new JSONArray();
        List<UpYun.FolderItem> items = upyun.readDir(SAVE_URL + appUser.getId() + "/");
        for (UpYun.FolderItem folderItem : items) {
            resultArray.add(folderItem);
        }
        resultJson.put("data", resultArray);
        resultJson.put("state", 1);
        resultJson.put("message", SuccessMessageEnum.OPERATE_SUCCESS);

        return resultJson;


    }

    public static void main(String[] args) throws Exception {
//        UpYun upyun = new UpYun(
//                "easylinker",
//                "18059150204",
//                "wwhlovejava123"
//        );
//
//        List<UpYun.FolderItem> items = upyun.readDir("/uploads/20171206");
//        for (int i = 0; i < items.size(); i++) {
//            System.out.println(items.get(i).name);
//            System.out.println(upyun.deleteFile(items.get(i).name));

//        }

        JSONArray jsonArray = new JSONArray();
        JSONObject resultJson = new JSONObject();

        jsonArray.add("wq543we5f3w4x254q534q");
        jsonArray.add("wq543we5f3w4x254q534q");
        jsonArray.add("wq543we5f3w4x254q534q");
        jsonArray.add("wq543we5f3w4x254q534q");
        resultJson.put("files", jsonArray);
        resultJson.put("state", 1);
        System.out.println(resultJson.toJSONString());

    }

}
