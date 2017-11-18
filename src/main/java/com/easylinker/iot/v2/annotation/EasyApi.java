package com.easylinker.iot.v2.annotation;

import java.lang.annotation.*;

/**
 * Created by wwhai on 2017/11/18.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EasyApi {
    String name() default "";

    String router() default "";

    String[] param() default {};

    String author() default "";

}
