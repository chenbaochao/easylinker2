package com.easylinker.iot.v2.annotation;

import java.lang.annotation.*;

/**
 * Created by wwhai on 2017/11/15.
 * 这个用来标记一个提供API的类
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EasyLinkerApiEntry {

}
