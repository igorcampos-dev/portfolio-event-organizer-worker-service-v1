package com.io.java.events.managers.domain.persistence;

import com.io.java.events.managers.application.dto.request.EventPutRequestDto;
import com.io.java.events.managers.application.dto.request.EventRequestDto;
import com.io.java.events.managers.application.dto.response.EventResponse;
import com.io.java.events.managers.application.dto.response.EventResponseGet;
import com.io.java.events.managers.application.dto.response.ListEventsResponse;
import java.time.LocalDateTime;
import java.util.List;

public interface EventPersistence {
    EventResponse saveEvent(EventRequestDto eventRequestDto);
    EventResponse updateEvent(EventPutRequestDto eventRequestDto);
    ListEventsResponse getEventByName(String name);
    EventResponseGet getEventByDate(LocalDateTime date);
}
