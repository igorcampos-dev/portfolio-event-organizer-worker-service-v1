package com.io.java.events.managers.application.controller.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static io.swagger.v3.oas.models.security.SecurityScheme.In.HEADER;

@Configuration
@SuppressWarnings("unused")
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()))
                .info(new Info()
                        .title("My REST API")
                        .description("Some custom description of API.")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Igor de campos")
                                .email("icampos.developer@yahoo.com")
                                .url("https://igorcampos-dev.github.io/"))
                        .license(new License()
                                .name("License of API")
                                .url("API license URL")));
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .in(HEADER)
                .scheme("bearer")
                .bearerFormat("JWT");
    }
}
