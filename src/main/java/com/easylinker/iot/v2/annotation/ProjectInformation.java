package com.easylinker.iot.v2.annotation;

/**
 * Created by wwhai on 2017/11/18.
 * 项目版权信息描述
 */

import java.lang.annotation.*;

/**
 * 项目名称 开始时间 结束时间 负责人 备注
 *
 * @return
 */
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ProjectInformation {

    String name() default "默认项目";

    String startTime() default "暂无开始时间";

    String endTime() default "暂无结束时间";

    String charger() default "暂无负责人";

    String remarks() default "暂无备注";

}
