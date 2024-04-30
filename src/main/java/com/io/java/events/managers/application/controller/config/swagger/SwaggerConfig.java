package com.io.java.events.managers.application.controller.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "My REST API",
        description = "Some custom description of API.",
        version = "1.0",
        contact = @Contact(
                name = "Igor de campos",
                email = "icampos.developer@yahoo.com",
                url = "https://igorcampos-dev.github.io/"
        ),
        license = @License(
                name = "License of API",
                url = "API license URL"
        )
))
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class SwaggerConfig {}
