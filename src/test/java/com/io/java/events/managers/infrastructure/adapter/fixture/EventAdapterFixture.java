package com.io.java.events.managers.infrastructure.adapter.fixture;

import com.io.java.events.managers.application.dto.request.EventPutRequestDto;
import com.io.java.events.managers.application.dto.request.EventRequestDto;
import com.io.java.events.managers.application.dto.request.Status;
import com.io.java.events.managers.infrastructure.entity.EventEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventAdapterFixture {

    public static final String NAME = "CACHE_NAME";
    public static final LocalDateTime DATE = LocalDateTime.parse("2024-05-24T10:15", DateTimeFormatter.ISO_LOCAL_DATE_TIME);

    public static EventRequestDto eventRequest() {
        return EventRequestDto.builder()
                .eventData(LocalDateTime.parse("2024-05-24T10:15:30", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .eventDescription("Test description")
                .eventName("Test name")
                .status(Status.ACTIVE)
                .build();
    }

    public static EventEntity eventEntity() {
        return EventEntity.builder()
                .date(LocalDateTime.parse("2024-05-24T10:15:30", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .name("Test name")
                .status(Status.ACTIVE)
                .description("Test description")
                .id("0a0aaaaa-0a00-aaaa-aaaa-aa0000a0aa00")
                .build();
    }

    public static EventPutRequestDto eventPutRequest() {
        return EventPutRequestDto.builder()
                .id("TEST ID")
                .eventData(LocalDateTime.parse("2024-05-24T10:15:30", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .eventDescription("TEST DESCRIPTION")
                .eventName("TEST NAME")
                .build();
    }
}
