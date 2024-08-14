package com.io.java.events.managers.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventResponseGet {

    @Schema(description = "Data dos eventos")
    private LocalDateTime date;

    @Schema(description = "Lista de eventos que vão acontecer nesta data")
    private List<EventsResponse> events;

    public static EventResponseGet buildClass(LocalDateTime date, List<EventsResponse> events){
        return EventResponseGet.builder()
                .date(date)
                .events(events)
                .build();
    }

}
