package com.io.java.events.managers.domain.service;

import com.io.java.events.managers.domain.persistence.EventPersistence;
import com.io.java.events.managers.domain.service.fixture.EventServiceFixture;
import com.io.java.events.managers.domain.service.impl.EventServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    @Mock
    private EventPersistence eventPersistence;

    @InjectMocks
    private EventServiceImpl service;

    @Test
    void shouldSaveEvent(){
        when(eventPersistence.saveEvent(EventServiceFixture.eventRequest()))
                .thenReturn(EventServiceFixture.eventSaveResponse());

        var result = service.saveEvent(EventServiceFixture.eventRequest());
        assertEquals(result, EventServiceFixture.eventSaveResponse());

    }

    @Test
    void shouldUpdateEvent(){
        when(eventPersistence.updateEvent(EventServiceFixture.eventPutRequest()))
                .thenReturn(EventServiceFixture.eventUpdateResponse());

        var result = service.updateEvent(EventServiceFixture.eventPutRequest());
        assertEquals(result, EventServiceFixture.eventUpdateResponse());
    }

    @Test
    void getEventByName(){
        when(eventPersistence.getEventByName(EventServiceFixture.NAME))
                .thenReturn(EventServiceFixture.eventGetByNameResponse());

        var result = service.getEventByName(EventServiceFixture.NAME);

        assertEquals(result, EventServiceFixture.eventGetByNameResponse());
    }

    @Test
    void getEventByDate(){
        when(eventPersistence.getEventByDate(EventServiceFixture.DATE))
                .thenReturn(EventServiceFixture.eventGetByDateResponse());

        var result = service.getEventByDate(EventServiceFixture.DATE);
        assertEquals(result, EventServiceFixture.eventGetByDateResponse());

    }

}
