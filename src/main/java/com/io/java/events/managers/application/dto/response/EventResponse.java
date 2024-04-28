package com.io.java.events.managers.application.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String message;
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
