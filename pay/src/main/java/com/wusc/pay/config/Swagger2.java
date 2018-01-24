package com.wusc.pay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * create by wusc on 2017/12/19
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wusc.campaign.controller"))
                .paths(PathSelectors.any())
                .build();
	}
	
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("campaign service api")
                .description("请把详细服务内容写到这")
                .contact(new Contact("wusc", "wusc.com", "1033429246@qq.com"))
                .version("1.0")
                .build();
    }
}
