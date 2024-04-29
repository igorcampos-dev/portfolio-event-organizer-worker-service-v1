package com.io.java.events.managers.infrastructure.mapper;

import com.io.java.events.managers.application.dto.response.ListEventsResponse;
import com.io.java.events.managers.infrastructure.entity.EventEntity;
import java.util.List;
import java.util.stream.Collectors;

public class EventMapper {

    public static ListEventsResponse eventEntityToEventResponseGet(EventEntity eventEntity){
        return ListEventsResponse.builder()
                .id(eventEntity.getId())
                .eventData(eventEntity.getDate())
                .eventDescription(eventEntity.getDescription())
                .eventName(eventEntity.getName())
                .status(eventEntity.getStatus())
                .build();
    }

    public static List<ListEventsResponse> eventEntityListToEventResponseGetList(List<EventEntity> eventEntityList) {
        if (eventEntityList.isEmpty()) throw new IllegalArgumentException("Sem eventos!");
        return eventEntityList.stream()
                .map(EventMapper::eventEntityToEventResponseGet)
                .collect(Collectors.toList());
    }
}
