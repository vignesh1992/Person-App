package com.application.person.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport{
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(true)
				.securitySchemes(Arrays.asList(bearerToken(), basicAuth())).select()
				.apis(RequestHandlerSelectors.basePackage("com.application.person"))
				.paths(Predicates.or(PathSelectors.ant("/people/**"), PathSelectors.ant("/authenticate/**"),
						PathSelectors.ant("/users/**")))
				.build().apiInfo(metaData());
	}
	
	private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Person Application")
                .description("Person Application to manage persons and users in system will be able to manage persons")
                .version("1.0.0")
                .license("General License")
                .build();
    }

	public static ApiKey bearerToken() {
		return new ApiKey("bearerToken", "Authorization", "header");

	}

	public static BasicAuth basicAuth() {
		return new BasicAuth("basicAuth");

	}
	
	@Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}