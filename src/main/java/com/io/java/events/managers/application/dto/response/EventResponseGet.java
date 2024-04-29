package com.io.java.events.managers.application.dto.response;

import com.io.java.events.managers.infrastructure.entity.EventEntity;
import com.io.java.events.managers.infrastructure.mapper.EventMapper;
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
    List<ListEventsResponse> events;

    public static EventResponseGet buildClass(LocalDateTime date, List<EventEntity> events){
        return EventResponseGet.builder()
                .date(date)
                .events(EventMapper.eventEntityListToEventResponseGetList(events))
                .build();
    }
}
