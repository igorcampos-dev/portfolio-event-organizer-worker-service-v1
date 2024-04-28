package com.io.java.events.managers.domain.service;

import com.io.java.events.managers.application.dto.request.EventPutRequestDto;
import com.io.java.events.managers.application.dto.request.EventRequestDto;
import com.io.java.events.managers.application.dto.response.EventResponse;
import com.io.java.events.managers.application.dto.response.EventResponseGet;
import java.time.LocalDateTime;
import java.util.List;

public interface EventService {
    EventResponse saveEvent(EventRequestDto eventRequestDto);
    EventResponse updateEvent(EventPutRequestDto eventRequestDto);
    EventResponseGet getEventByName(String name);
    List<EventResponseGet> getEventByDate(LocalDateTime date);
}
