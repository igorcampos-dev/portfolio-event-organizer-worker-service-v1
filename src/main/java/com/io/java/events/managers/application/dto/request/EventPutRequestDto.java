package com.io.java.events.managers.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record EventPutRequestDto(

        @NotNull(message = "O id do evento não pode ser nulo.")
        @NotBlank(message = "O id do evento não pode estar em branco.")
        String id,

        @NotNull(message = "O nome do evento não pode ser nulo.")
        @NotBlank(message = "O nome do evento não pode estar em branco.")
        String eventName,

        @NotNull(message = "A descrição do evento não pode ser nula.")
        @NotBlank(message = "A descrição do evento não pode estar em branco.")
        String eventDescription,

        @NotNull(message = "A data do evento não pode ser nula.")
        LocalDateTime eventData,

        @NotNull(message = "O status do evento não pode ser nulo.")
        Status status
) {}
