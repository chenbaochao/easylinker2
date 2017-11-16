package com.easylinker.iot.v2.annotation;

/**
 * Created by wwhai on 2017/11/15.
 */
public @interface Api {
    String name() default "";

    String router() default "";

    String[] paraments() default {};

}
