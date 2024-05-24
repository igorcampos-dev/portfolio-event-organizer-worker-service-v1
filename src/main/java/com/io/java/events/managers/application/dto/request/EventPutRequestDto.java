package com.io.java.events.managers.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record EventPutRequestDto(

        @NotNull(message = "O id do evento não pode ser nulo.")
        @NotBlank(message = "O id do evento não pode estar em branco.")
        @Schema(description = "Id do evento que será atualizado")
        String id,

        @NotNull(message = "O nome do evento não pode ser nulo.")
        @NotBlank(message = "O nome do evento não pode estar em branco.")
        @Schema(description = "Nome do evento que será atualizado")
        String eventName,

        @NotNull(message = "A descrição do evento não pode ser nula.")
        @NotBlank(message = "A descrição do evento não pode estar em branco.")
        @Schema(description = "Descrição do evento que será atualizado")
        String eventDescription,

        @NotNull(message = "A data do evento não pode ser nula.")
        @Schema(description = "Data do evento que será atualizado")
        LocalDateTime eventData,

        @NotNull(message = "O status do evento não pode ser nulo.")
        @Schema(description = "Status do evento que será atualizado")
        Status status
) {}
