package com.io.java.events.managers.domain.service;

import com.io.java.events.managers.application.dto.request.EventPutRequestDto;
import com.io.java.events.managers.application.dto.request.EventRequestDto;
import com.io.java.events.managers.application.dto.response.EventResponse;
import com.io.java.events.managers.application.dto.response.EventResponseGet;
import com.io.java.events.managers.application.dto.response.ListEventsResponse;
import com.io.java.events.managers.domain.persistence.EventPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventPersistence eventPersistence;

    @Override
    public EventResponse saveEvent(EventRequestDto eventRequestDto) {
        return eventPersistence.saveEvent(eventRequestDto);
    }

    @Override
    public EventResponse updateEvent(EventPutRequestDto eventRequestDto) {
        return eventPersistence.updateEvent(eventRequestDto);
    }

    @Override
    public ListEventsResponse getEventByName(String name) {
        return eventPersistence.getEventByName(name);
    }

    @Override
    public EventResponseGet getEventByDate(LocalDateTime date) {
        return eventPersistence.getEventByDate(date);
    }
}
