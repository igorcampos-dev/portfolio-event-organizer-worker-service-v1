package com.io.java.events.managers.infrastructure.adapter;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.io.java.events.managers.application.dto.request.EventPutRequestDto;
import com.io.java.events.managers.application.dto.request.EventRequestDto;
import com.io.java.events.managers.application.dto.response.EventResponse;
import com.io.java.events.managers.application.dto.response.EventResponseGet;
import com.io.java.events.managers.domain.persistence.EventPersistence;
import com.io.java.events.managers.infrastructure.entity.EventEntity;
import com.io.java.events.managers.infrastructure.mapper.EventMapper;
import com.io.java.events.managers.infrastructure.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class EventAdapter implements EventPersistence {

    private final EventRepository eventRepository;
    private final Cache<String, EventEntity> cache;

    public EventAdapter(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
        cache = Caffeine.newBuilder()
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .build();
    }

    @Override
    public EventResponse saveEvent(EventRequestDto eventRequestDto) {

      eventRepository.saveOrElseThrow(
                EventEntity.builder()
                        .date(eventRequestDto.eventData())
                        .status(eventRequestDto.status())
                        .description(eventRequestDto.eventDescription())
                        .name(eventRequestDto.eventName())
                        .build()
        );



    return EventResponse.responseStatusSuccessSave();
    }

    @Override
    public EventResponse updateEvent(EventPutRequestDto eventRequestDto) {
        eventRepository.updateEventOrElseThrow(
                EventEntity.builder()
                        .id(eventRequestDto.id())
                        .date(eventRequestDto.eventData())
                        .status(eventRequestDto.status())
                        .description(eventRequestDto.eventDescription())
                        .name(eventRequestDto.eventName())
                        .build()
        );

        cache.invalidate(eventRequestDto.eventName());
        return EventResponse.responseStatusSuccessUpdate();
    }

    @Override
    public EventResponseGet getEventByName(String name) {

        EventEntity eventEntity = cache.getIfPresent(name);

        if (eventEntity != null)
            return EventMapper.eventEntityToEventResponseGet(eventEntity);

        var event =  eventRepository.findByNameOrElseThrow(name);
        cache.put(event.getName(), event);
        return EventMapper.eventEntityToEventResponseGet(event);
    }

    @Override
    public List<EventResponseGet> getEventByDate(LocalDateTime date) {
        var response = eventRepository.findByDateOrElseThrow(date);
        return EventMapper.eventEntityListToEventResponseGetList(response);
    }
}
