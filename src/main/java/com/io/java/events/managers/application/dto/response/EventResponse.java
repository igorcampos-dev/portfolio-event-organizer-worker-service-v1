package com.io.java.events.managers.application.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventResponse {

    @Schema(description = "Mensagem de retorno do evento")
    private String message;

    @Schema(description = "Horário em que houve a ação")
    private LocalDate timestamp;

    @JsonIgnore
    private HttpStatus status;

    public static EventResponse responseStatusSuccessSave() {
        return EventResponse.builder()
                .timestamp(LocalDate.now())
                .status(HttpStatus.OK)
                .message("Evento salvo com sucesso.")
                .build();
    }

    public static EventResponse responseStatusSuccessUpdate() {
        return EventResponse.builder()
                .timestamp(LocalDate.now())
                .status(HttpStatus.OK)
                .message("Evento atualizado com sucesso.")
                .build();
    }

}
