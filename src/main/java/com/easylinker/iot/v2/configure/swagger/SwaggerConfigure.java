package com.easylinker.iot.v2.configure.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Created by wwhai on 2017/11/22.
 */
@Configuration
public class SwaggerConfigure  {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()

                .apis(RequestHandlerSelectors.basePackage("com.easylinker.iot.v2.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("EasyLinker RESTFUL APIs(HTTP)")
                .description("相关文章请关注：http://www.easylinker.xyz/")
                .termsOfServiceUrl("http://www.easylinker.xyz/")
                .contact("wwhai:751957846@qq.com")
                .license("GPLV2")
                .version("2.0")
                .build();
    }
}
