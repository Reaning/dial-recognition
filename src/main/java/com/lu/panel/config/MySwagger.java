package com.lu.panel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
@EnableOpenApi
public class MySwagger {
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Lu")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lu.panel.controller"))
                .build()
                .apiInfo(apiInfo());
    }
    private ApiInfo apiInfo(){
        Contact contact = new Contact("19计科一班卢科雄","","lkxBruce@gmail.com");
        return new ApiInfo("仪表识别接口",
                "提供仪表识别的api接口",
                "v1.0",
                "",
                contact,
                "Apach 2.0 许可",
                "许可链接",
                new ArrayList<>()
        );
    }
}
