package com.io.java.events.managers.domain.service.fixture;

import com.io.java.events.managers.application.dto.request.EventPutRequestDto;
import com.io.java.events.managers.application.dto.request.EventRequestDto;
import com.io.java.events.managers.application.dto.request.Status;
import com.io.java.events.managers.application.dto.response.EventResponse;
import com.io.java.events.managers.application.dto.response.EventResponseGet;
import com.io.java.events.managers.application.dto.response.EventsResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EventServiceFixture {

    public static final String NAME = "NAME TEST";
    public static final LocalDateTime DATE = LocalDateTime.parse("2024-05-24T10:15", DateTimeFormatter.ISO_LOCAL_DATE_TIME);


    public static EventRequestDto eventRequest() {
        return EventRequestDto.builder()
                .eventDescription("TEST DESCRIPTION")
                .eventName("TEST NAME")
                .eventData(LocalDateTime.parse("2024-05-24T10:15:30", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .status(Status.ACTIVE)
                .build();
    }

    public static EventResponse eventSaveResponse() {
        return EventResponse.responseStatusSuccessSave();
    }

    public static EventPutRequestDto eventPutRequest() {
        return EventPutRequestDto.builder()
                .id("TEST ID")
                .eventData(LocalDateTime.parse("2024-05-24T10:15:30", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .eventDescription("TEST DESCRIPTION")
                .eventName("TEST NAME")
                .build();
    }

    public static EventResponse eventUpdateResponse() {
        return EventResponse.responseStatusSuccessUpdate();
    }

    public static EventsResponse eventGetByNameResponse() {
        return EventsResponse.builder()
                .id("0a0aaaaa-0a00-aaaa-aaaa-aa0000a0aa00")
                .eventData(LocalDateTime.parse("2024-05-24T10:15:30", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .eventDescription("Test description")
                .eventName("Test name")
                .status(Status.ACTIVE)
                .build();
    }

    public static EventResponseGet eventGetByDateResponse() {
        return EventResponseGet.builder()
                .date(LocalDateTime.parse("2024-05-24T10:15:30", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .events(List.of(eventGetByNameResponse()))
                .build();
    }

}
