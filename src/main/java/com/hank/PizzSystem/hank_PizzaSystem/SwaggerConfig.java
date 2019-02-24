package com.hank.PizzSystem.hank_PizzaSystem;

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

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket createRestApi() {
    ApiInfo apiInfo = new ApiInfoBuilder()
        .title("使用Swagger2 Docs")
        .description("API Document ")
        .termsOfServiceUrl("http://localost:8080")
        .contact(new Contact("Fiona", "test.com", "fiona@test.com"))
        .version("1.0.0")
        .build();

    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.fiona.demo"))
        .paths(PathSelectors.any())
        .build();
  }
}