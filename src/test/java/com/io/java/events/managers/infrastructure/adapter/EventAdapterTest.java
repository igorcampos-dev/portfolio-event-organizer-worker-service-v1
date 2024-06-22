package com.io.java.events.managers.infrastructure.adapter;

import com.github.benmanes.caffeine.cache.Cache;
import com.io.java.events.managers.infrastructure.adapter.fixture.EventAdapterFixture;
import com.io.java.events.managers.infrastructure.entity.EventEntity;
import com.io.java.events.managers.infrastructure.mapper.EventOperationMapper;
import com.io.java.events.managers.infrastructure.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventAdapterTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private Cache<String, EventEntity> cache;

    @Mock
    private Cache<LocalDateTime, List<EventEntity>> cacheList;

    @Mock
    private EventOperationMapper eventOperationMapper;

    @InjectMocks
    private EventAdapter eventAdapter;

    @Test
    void shouldNotSaveEvent_eventAlreadyExists(){

        when(eventOperationMapper.toEntity(EventAdapterFixture.eventRequest()))
                .thenReturn(EventAdapterFixture.eventEntity());

        doThrow(new DuplicateKeyException("Event already exists"))
                .when(eventRepository).saveOrElseThrow(EventAdapterFixture.eventEntity());

        assertThrows(DuplicateKeyException.class, () -> eventAdapter.saveEvent(EventAdapterFixture.eventRequest()));

        verify(eventRepository, times(1)).saveOrElseThrow(EventAdapterFixture.eventEntity());
        verify(cacheList, never()).invalidate(any(LocalDateTime.class));
    }

    @Test
    void shouldNotUpdateEvent_eventNotExists(){
        when(eventOperationMapper.toEntity(EventAdapterFixture.eventPutRequest()))
                .thenReturn(EventAdapterFixture.eventEntity());

        doThrow(new DuplicateKeyException("Event already exists"))
                .when(eventRepository).updateEventOrElseThrow(EventAdapterFixture.eventEntity());

        assertThrows(DuplicateKeyException.class, () -> eventAdapter.updateEvent(EventAdapterFixture.eventPutRequest()));

        verify(eventRepository, times(1)).updateEventOrElseThrow(EventAdapterFixture.eventEntity());
        verify(cache, never()).invalidate(any(String.class));
        verify(cacheList, never()).invalidate(any(LocalDateTime.class));
    }

    @Test
    void shouldGetByNameEvent_eventNotExists(){
        doThrow(new NullPointerException())
                .when(eventRepository).findByNameOrElseThrow(EventAdapterFixture.NAME);

        assertThrows(NullPointerException.class, () -> eventAdapter.getEventByName(EventAdapterFixture.NAME));

        verify(eventRepository, times(1)).findByNameOrElseThrow(any(String.class));
        verify(cache, never()).put(any(String.class), any(EventEntity.class));
    }

    @Test
    void shouldGetByNameEvent(){

        when(eventRepository.findByNameOrElseThrow(EventAdapterFixture.NAME))
                .thenReturn(EventAdapterFixture.eventEntity());

        eventAdapter.getEventByName(EventAdapterFixture.NAME);

        verify(eventRepository, times(1)).findByNameOrElseThrow(any(String.class));
    }


    @Test
    void shouldGetByDateEvent_eventNotExists(){
        doThrow(new NullPointerException())
                .when(eventRepository).findByDateOrElseThrow(EventAdapterFixture.DATE);

        assertThrows(NullPointerException.class, () -> eventAdapter.getEventByDate(EventAdapterFixture.DATE));

        verify(eventRepository, times(1)).findByDateOrElseThrow(any(LocalDateTime.class));
        verify(cacheList, never()).put(any(LocalDateTime.class), any());
    }

    @Test
    void shouldGetByDateEvent(){

        when(eventRepository.findByDateOrElseThrow(EventAdapterFixture.DATE))
                .thenReturn(List.of(EventAdapterFixture.eventEntity()));

        eventAdapter.getEventByDate(EventAdapterFixture.DATE);

        verify(eventRepository, times(1)).findByDateOrElseThrow(any(LocalDateTime.class));
    }

}
