package com.io.java.events.managers.infrastructure.adapter;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.io.java.events.managers.application.dto.request.EventPutRequestDto;
import com.io.java.events.managers.application.dto.request.EventRequestDto;
import com.io.java.events.managers.application.dto.response.EventResponse;
import com.io.java.events.managers.application.dto.response.EventResponseGet;
import com.io.java.events.managers.application.dto.response.ListEventsResponse;
import com.io.java.events.managers.domain.persistence.EventPersistence;
import com.io.java.events.managers.infrastructure.entity.EventEntity;
import com.io.java.events.managers.infrastructure.mapper.EventMapper;
import com.io.java.events.managers.infrastructure.repository.EventRepository;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@SuppressWarnings("unused")
public class EventAdapter implements EventPersistence {

    private final EventRepository eventRepository;
    private final Cache<String, EventEntity> cache;
    private final Cache<LocalDateTime, List<EventEntity>> cacheList;

    public EventAdapter(EventRepository eventRepository) {
        this.eventRepository = eventRepository;

        cache = Caffeine.newBuilder()
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .build();

        cacheList = Caffeine.newBuilder()
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .build();
    }

    @Override
    public EventResponse saveEvent(EventRequestDto eventRequestDto) {
      eventRepository.saveOrElseThrow(EventEntity.buildClassWithoutId(eventRequestDto));
      cacheList.invalidate(eventRequestDto.eventData());
      return EventResponse.responseStatusSuccessSave();
    }

    @Override
    public EventResponse updateEvent(EventPutRequestDto eventRequestDto) {
        eventRepository.updateEventOrElseThrow(EventEntity.buildClass(eventRequestDto));
        cache.invalidate(eventRequestDto.eventName());
        cacheList.invalidate(eventRequestDto.eventData());
        return EventResponse.responseStatusSuccessUpdate();
    }

    @Override
    public ListEventsResponse getEventByName(String name) {
        EventEntity eventEntity = cache.getIfPresent(name);

        if (eventEntity != null)
            return EventMapper.eventEntityToEventResponseGet(eventEntity);

        var event =  eventRepository.findByNameOrElseThrow(name);
        cache.put(event.getName(), event);
        return EventMapper.eventEntityToEventResponseGet(event);
    }

    @Override
    public EventResponseGet getEventByDate(LocalDateTime date) {

        List<EventEntity> eventsCache = cacheList.getIfPresent(date);

        if (eventsCache != null)
            return EventResponseGet.buildClass(date, eventsCache);

        var events = eventRepository.findByDateOrElseThrow(date);
        cacheList.put(date, events);
        return EventResponseGet.buildClass(date, events);
    }
}
