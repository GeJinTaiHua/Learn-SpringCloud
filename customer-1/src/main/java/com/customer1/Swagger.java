package com.customer1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class Swagger {

    @Value("${swagger.enable}")
    private boolean enableSwagger;

    @Bean
    public Docket createRestApi() {
        List<Parameter> parameters = new ArrayList<>();

        ParameterBuilder parameterBuilder = new ParameterBuilder();
        parameterBuilder.name("tmx-auth-token").description("tmx-auth-token")
                .modelRef(new ModelRef("string")).parameterType("header")
                .defaultValue("token_demo_dev_13600")
                .required(false).build();
        parameters.add(parameterBuilder.build());

        ParameterBuilder parameterBuilder2 = new ParameterBuilder();
        parameterBuilder2.name("tmx-user-id").description("tmx-user-id")
                .modelRef(new ModelRef("string")).parameterType("header")
                .defaultValue("t112dfa45545155151")
                .required(false).build();
        parameters.add(parameterBuilder2.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(enableSwagger)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.customer1.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(parameters);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API title")
                .description("API escription")
                .termsOfServiceUrl("API termsOfServiceUrl")
                .version("1.0")
                .build();
    }
}