package com.io.java.events.managers.application.dto.response;

import com.io.java.events.managers.application.dto.request.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventsResponse {

    @Schema(description = "Id do evento")
    private String id;

    @Schema(description = "Descrição do evento")
    private String eventDescription;

    @Schema(description = "Nome do evento")
    private String eventName;

    @Schema(description = "Data do evento")
    private LocalDateTime eventData;

    @Schema(description = "Status do evento")
    private Status status;
}
