package com.easylinker.iot.v2.except.framework;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Created by wwhai on 2017/11/15.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 系统异常处理，比如：404,500
     *
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JSONObject defaultErrorHandler(Exception e) {
        JSONObject resultJson = new JSONObject();
        if (e instanceof NoHandlerFoundException) {
            resultJson.put("state", 0);
            resultJson.put("message", "Error code 404! Resource not found!");
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            resultJson.put("state", 0);
            resultJson.put("message", "Error code 500! HTTP Method not support，please check out your http request method!");
        } else if (e instanceof NullPointerException) {
            resultJson.put("state", 0);
            resultJson.put("message", "Error code 500! Required params not present!");

        } else if (e instanceof HttpMessageNotReadableException) {
            resultJson.put("state", 0);
            resultJson.put("message", "Error code 500! Required params not present!");
        }
        return resultJson;
    }

}
