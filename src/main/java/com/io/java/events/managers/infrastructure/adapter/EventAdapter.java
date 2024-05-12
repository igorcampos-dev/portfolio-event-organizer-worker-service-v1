package com.io.java.events.managers.infrastructure.adapter;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.io.java.events.managers.application.dto.request.EventPutRequestDto;
import com.io.java.events.managers.application.dto.request.EventRequestDto;
import com.io.java.events.managers.application.dto.response.EventResponse;
import com.io.java.events.managers.application.dto.response.EventResponseGet;
import com.io.java.events.managers.application.dto.response.EventsResponse;
import com.io.java.events.managers.domain.persistence.EventPersistence;
import com.io.java.events.managers.infrastructure.entity.EventEntity;
import com.io.java.events.managers.infrastructure.mapper.EventOperationMapper;
import com.io.java.events.managers.infrastructure.repository.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@SuppressWarnings("unused")
public class EventAdapter implements EventPersistence {

    private final EventRepository eventRepository;
    private final Cache<String, EventEntity> cache;
    private final Cache<LocalDateTime, List<EventEntity>> cacheList;
    private final EventOperationMapper eventOperationMapper;

    public EventAdapter(EventRepository eventRepository, EventOperationMapper eventOperationMapper) {
        this.eventRepository = eventRepository;
        this.eventOperationMapper = eventOperationMapper;

        cache = Caffeine.newBuilder()
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .build();

        cacheList = Caffeine.newBuilder()
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .build();
    }

    @Override
    public EventResponse saveEvent(EventRequestDto eventRequestDto) {
      eventRepository.saveOrElseThrow(eventOperationMapper.toEntity(eventRequestDto));
      log.info("evento salvo");
      cacheList.invalidate(eventRequestDto.eventData());
      log.info("invalidamos o cache por data, tudo certo");
      return EventResponse.responseStatusSuccessSave();
    }

    @Override
    public EventResponse updateEvent(EventPutRequestDto eventRequestDto) {
        eventRepository.updateEventOrElseThrow(eventOperationMapper.toEntity(eventRequestDto));
        log.info("atualizamos o evento");
        cache.invalidate(eventRequestDto.eventName());
        cacheList.invalidate(eventRequestDto.eventData());
        log.info("invalidamos ambos os caches,por lista e por nome com base na data, tudo certo");
        return EventResponse.responseStatusSuccessUpdate();
    }

    @Override
    public EventsResponse getEventByName(String name) {
        EventEntity eventCache = cache.getIfPresent(name);

        if (eventCache != null) {
            log.info("evento existe no cache, retornando ele");
            return eventOperationMapper.toResponse(eventCache);
        }

        var eventEntity =  eventRepository.findByNameOrElseThrow(name);
        log.info("evento não existe em cache, vamos buscar,salvar no cache e retornar ele");
        cache.put(eventEntity.getName(), eventEntity);
        return eventOperationMapper.toResponse(eventEntity);
    }

    @Override
    public EventResponseGet getEventByDate(LocalDateTime date) {
        List<EventEntity> eventsCache = cacheList.getIfPresent(date);

        if (eventsCache != null) {
            log.info("evento/eventos existem no cache,retornando ele(s)");
            return EventResponseGet.buildClass(date, eventOperationMapper.toResponseListOrElseThrow(eventsCache));
        }
        var events = eventRepository.findByDateOrElseThrow(date);
        log.info("eventos não existe em cache, vamos buscar,salvar no cache e retornar ele");
        cacheList.put(date, events);
        return EventResponseGet.buildClass(date, eventOperationMapper.toResponseListOrElseThrow(events));
    }

}
