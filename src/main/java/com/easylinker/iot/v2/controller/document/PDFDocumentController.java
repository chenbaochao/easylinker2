package com.easylinker.iot.v2.controller.document;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wwhai on 2017/11/18.
 */
@RestController
@RequestMapping("/api/pdf")
public class PDFDocumentController {
    private JSONObject resJson = new JSONObject();

    @RequestMapping("/getDocument")
    public JSONObject getDocument() {

        resJson.put("state", 1);
        resJson.put("message", "Ok");
        return resJson;

    }
}
