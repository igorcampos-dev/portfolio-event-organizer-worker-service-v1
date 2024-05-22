package com.io.java.events.managers.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UserRequestDto(

        @NotNull(message = "O email não pode ser nulo.")
        @NotBlank(message = "O email não pode estar em branco.")
        @Email(message = "Por favor, use um email válido.")
        @Schema(description = "Email do usuário que será feito o login")
        String email,

        @NotNull(message = "A senha não pode ser nula.")
        @NotBlank(message = "A senha não pode estar em branco.")
        @Schema(description = "Senha do usuário que será feito o login")
        String password

) {}
