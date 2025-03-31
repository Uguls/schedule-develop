package com.sparta.schedule_develop;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("일정 관리 API 명세서")
                .description("스프링부트 + Swagger + OpenAPI 3.0 문서 예제")
                .version("1.0.0")
                .contact(new Contact()
                        .name("박형우"));
    }
}
