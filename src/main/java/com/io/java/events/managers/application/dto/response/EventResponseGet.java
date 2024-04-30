package com.io.java.events.managers.application.dto.response;

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
    LocalDateTime date;
    List<EventsResponse> events;

    public static EventResponseGet buildClass(LocalDateTime date, List<EventsResponse> events){
        return EventResponseGet.builder()
                .date(date)
                .events(events)
                .build();
    }
}
