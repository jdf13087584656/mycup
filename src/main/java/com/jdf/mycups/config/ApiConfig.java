package com.jdf.mycups.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
public class ApiConfig {
    @Bean
      public Docket createRestApi() {
                 return new Docket(DocumentationType.SWAGGER_2)
                         .apiInfo(apiInfo())
                       .select()
                         .apis(RequestHandlerSelectors.basePackage("com.jdf.mycups"))
                         .paths(PathSelectors.any())
                         .build();
            }
    private ApiInfo apiInfo() {
                return new ApiInfoBuilder()
                         .title("MyCups的RESTful APIs")
                        .description("MyCups的RESTful")
                         .termsOfServiceUrl("http://blog.didispace.com/")
                         .contact("jdf")
                        .version("1.0")
                        .build();
    }
}
