package com.io.java.events.managers.application.controller.v1;

import com.io.java.events.managers.application.config.TestConfig;
import com.io.java.events.managers.application.controller.v1.fixture.EventControllerFixture;
import com.io.java.events.managers.application.dto.request.EventPutRequestDto;
import com.io.java.events.managers.application.dto.request.EventRequestDto;
import com.io.java.events.managers.application.utils.FileUtils;
import com.io.java.events.managers.application.utils.Objects;
import com.io.java.events.managers.application.utils.URLS;
import com.io.java.events.managers.domain.service.EventService;
import com.io.java.events.managers.security.SecurityConfig;
import com.io.java.events.managers.security.filter.Filter;
import com.io.java.events.managers.security.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EventController.class)
@Import({EventController.class, SecurityConfig.class, Filter.class})
@ContextConfiguration(classes = TestConfig.class)
public class EventControllerTest {

    @Autowired
    @SuppressWarnings("unused")
    private MockMvc mvc;

    @MockBean
    @SuppressWarnings("unused")
    private EventService eventService;

    @MockBean
    @SuppressWarnings("unused")
    private JwtUtil jwtUtil;

    @MockBean
    private Objects obj;


    @Test
    void shouldSaveEvent() throws Exception {

        when(jwtUtil.isValidToken(EventControllerFixture.JWT)).thenReturn(true);
        when(jwtUtil.authenticate(EventControllerFixture.JWT)).thenReturn(EventControllerFixture.authentication());

        when(eventService.saveEvent(EventControllerFixture.eventRequest()))
                .thenReturn(EventControllerFixture.eventSuccessResponseSave());

        var result = mvc.perform(
                post(URLS.EVENT_PATH)
                        .header("Authorization", EventControllerFixture.JWT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(FileUtils.readFileFromClassLoader("json/request/event-save-request.json"))
        ).andExpect(status().isCreated()).andReturn();

        verify(eventService, times(1)).saveEvent(any(EventRequestDto.class));
        assertTrue(FileUtils.hasFields(result.getResponse().getContentAsString(), "message", "timestamp"));

    }

    @Test
    void shouldNotSaveEvent_withoutEventName() throws Exception {

        String jsonSave = FileUtils.readFileFromClassLoader("json/request/event-save-request.json");
        jsonSave = FileUtils.removeField(jsonSave, "eventName");


        when(jwtUtil.isValidToken(EventControllerFixture.JWT)).thenReturn(true);
        when(jwtUtil.authenticate(EventControllerFixture.JWT)).thenReturn(EventControllerFixture.authentication());

        mvc.perform(
                post(URLS.EVENT_PATH)
                        .header("Authorization", EventControllerFixture.JWT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(jsonSave)
       ).andExpect(status().isBadRequest()).andReturn();

        verify(eventService, never()).saveEvent(any(EventRequestDto.class));

    }

    @Test
    void shouldNotSaveEvent_withoutEventDescription() throws Exception {

        String jsonSave = FileUtils.readFileFromClassLoader("json/request/event-save-request.json");
        jsonSave = FileUtils.removeField(jsonSave, "eventDescription");

        when(jwtUtil.isValidToken(EventControllerFixture.JWT)).thenReturn(true);
        when(jwtUtil.authenticate(EventControllerFixture.JWT)).thenReturn(EventControllerFixture.authentication());

        mvc.perform(
                post(URLS.EVENT_PATH)
                        .header("Authorization", EventControllerFixture.JWT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(jsonSave)
        ).andExpect(status().isBadRequest()).andReturn();

        verify(eventService, never()).saveEvent(any(EventRequestDto.class));

    }

    @Test
    void shouldNotSaveEvent_withoutEventData() throws Exception {

        String jsonSave = FileUtils.readFileFromClassLoader("json/request/event-save-request.json");
        jsonSave = FileUtils.removeField(jsonSave, "eventData");

        when(jwtUtil.isValidToken(EventControllerFixture.JWT)).thenReturn(true);
        when(jwtUtil.authenticate(EventControllerFixture.JWT)).thenReturn(EventControllerFixture.authentication());

        mvc.perform(
                post(URLS.EVENT_PATH)
                        .header("Authorization", EventControllerFixture.JWT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(jsonSave)
        ).andExpect(status().isBadRequest()).andReturn();

        verify(eventService, never()).saveEvent(any(EventRequestDto.class));

    }

    @Test
    void shouldNotSaveEvent_withoutStatus() throws Exception {

        String jsonSave = FileUtils.readFileFromClassLoader("json/request/event-save-request.json");
        jsonSave = FileUtils.removeField(jsonSave, "status");

        when(jwtUtil.isValidToken(EventControllerFixture.JWT)).thenReturn(true);
        when(jwtUtil.authenticate(EventControllerFixture.JWT)).thenReturn(EventControllerFixture.authentication());

        mvc.perform(
                post(URLS.EVENT_PATH)
                        .header("Authorization", EventControllerFixture.JWT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(jsonSave)
        ).andExpect(status().isBadRequest()).andReturn();

        verify(eventService, never()).saveEvent(any(EventRequestDto.class));

    }

    @Test
    void shouldNotSaveEvent_withoutToken() throws Exception {
        mvc.perform(
                post(URLS.EVENT_PATH)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(FileUtils.readFileFromClassLoader("json/request/event-save-request.json"))
        ).andExpect(status().isForbidden()).andReturn();

        verify(eventService, never()).saveEvent(any(EventRequestDto.class));

    }

    @Test
    void shouldPutEvent() throws Exception {

        when(jwtUtil.isValidToken(EventControllerFixture.JWT)).thenReturn(true);
        when(jwtUtil.authenticate(EventControllerFixture.JWT)).thenReturn(EventControllerFixture.authentication());

        when(eventService.updateEvent(EventControllerFixture.eventRequestPut()))
                .thenReturn(EventControllerFixture.eventSuccessResponseUpdate());

        var result = mvc.perform(
                put(URLS.EVENT_PATH)
                        .header("Authorization", EventControllerFixture.JWT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(FileUtils.readFileFromClassLoader("json/request/event-update-request.json"))
        ).andExpect(status().isOk()).andReturn();

        verify(eventService, times(1)).updateEvent(any(EventPutRequestDto.class));
        assertTrue(FileUtils.hasFields(result.getResponse().getContentAsString(), "message", "timestamp"));

    }

    @Test
    void shouldNotUpdateEvent_withoutId() throws Exception {

        String jsonSave = FileUtils.readFileFromClassLoader("json/request/event-update-request.json");
        jsonSave = FileUtils.removeField(jsonSave, "id");

        when(jwtUtil.isValidToken(EventControllerFixture.JWT)).thenReturn(true);
        when(jwtUtil.authenticate(EventControllerFixture.JWT)).thenReturn(EventControllerFixture.authentication());

        mvc.perform(
                put(URLS.EVENT_PATH)
                        .header("Authorization", EventControllerFixture.JWT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(jsonSave)
        ).andExpect(status().isBadRequest()).andReturn();

        verify(eventService, never()).updateEvent(any(EventPutRequestDto.class));

    }

    @Test
    void shouldNotUpdateEvent_withoutStatus() throws Exception {

        String jsonSave = FileUtils.readFileFromClassLoader("json/request/event-update-request.json");
        jsonSave = FileUtils.removeField(jsonSave, "status");

        when(jwtUtil.isValidToken(EventControllerFixture.JWT)).thenReturn(true);
        when(jwtUtil.authenticate(EventControllerFixture.JWT)).thenReturn(EventControllerFixture.authentication());

        mvc.perform(
                put(URLS.EVENT_PATH)
                        .header("Authorization", EventControllerFixture.JWT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(jsonSave)
        ).andExpect(status().isBadRequest()).andReturn();

        verify(eventService, never()).updateEvent(any(EventPutRequestDto.class));

    }

    @Test
    void shouldNotUpdateEvent_withoutEventName() throws Exception {

        String jsonSave = FileUtils.readFileFromClassLoader("json/request/event-update-request.json");
        jsonSave = FileUtils.removeField(jsonSave, "eventName");

        when(jwtUtil.isValidToken(EventControllerFixture.JWT)).thenReturn(true);
        when(jwtUtil.authenticate(EventControllerFixture.JWT)).thenReturn(EventControllerFixture.authentication());

        mvc.perform(
                put(URLS.EVENT_PATH)
                        .header("Authorization", EventControllerFixture.JWT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(jsonSave)
        ).andExpect(status().isBadRequest()).andReturn();

        verify(eventService, never()).updateEvent(any(EventPutRequestDto.class));

    }

    @Test
    void shouldNotUpdateEvent_withoutEventDescription() throws Exception {

        String jsonSave = FileUtils.readFileFromClassLoader("json/request/event-update-request.json");
        jsonSave = FileUtils.removeField(jsonSave, "eventDescription");

        when(jwtUtil.isValidToken(EventControllerFixture.JWT)).thenReturn(true);
        when(jwtUtil.authenticate(EventControllerFixture.JWT)).thenReturn(EventControllerFixture.authentication());

        mvc.perform(
                put(URLS.EVENT_PATH)
                        .header("Authorization", EventControllerFixture.JWT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(jsonSave)
        ).andExpect(status().isBadRequest()).andReturn();

        verify(eventService, never()).updateEvent(any(EventPutRequestDto.class));

    }

    @Test
    void shouldNotUpdateEvent_withoutEventData() throws Exception {

        String jsonSave = FileUtils.readFileFromClassLoader("json/request/event-update-request.json");
        jsonSave = FileUtils.removeField(jsonSave, "eventData");

        when(jwtUtil.isValidToken(EventControllerFixture.JWT)).thenReturn(true);
        when(jwtUtil.authenticate(EventControllerFixture.JWT)).thenReturn(EventControllerFixture.authentication());

        mvc.perform(
                put(URLS.EVENT_PATH)
                        .header("Authorization", EventControllerFixture.JWT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(jsonSave)
        ).andExpect(status().isBadRequest()).andReturn();

        verify(eventService, never()).updateEvent(any(EventPutRequestDto.class));

    }

    @Test
    void shouldNotUpdateEvent_withoutToken() throws Exception {
        mvc.perform(
                post(URLS.EVENT_PATH)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(FileUtils.readFileFromClassLoader("json/request/event-update-request.json"))
        ).andExpect(status().isForbidden()).andReturn();

        verify(eventService, never()).updateEvent(any(EventPutRequestDto.class));

    }

    @Test
    void shouldGetEventByName() throws Exception {

        when(jwtUtil.isValidToken(EventControllerFixture.JWT)).thenReturn(true);
        when(jwtUtil.authenticate(EventControllerFixture.JWT)).thenReturn(EventControllerFixture.authentication());

        when(eventService.getEventByName(EventControllerFixture.HEADER_NAME))
                .thenReturn(EventControllerFixture.eventGetByNameResponse());

        var result = mvc.perform(
                get(URLS.FINAL_EVENT_GET_BY_NAME)
                        .header("Authorization", EventControllerFixture.JWT)
                        .header("name", EventControllerFixture.HEADER_NAME)
        ).andExpect(status().isOk()).andReturn();

        verify(eventService, times(1)).getEventByName(any(String.class));
        assertEquals(FileUtils.readFileFromClassLoader("json/expected/event-get-name-expected.json"), result.getResponse().getContentAsString());
    }

    @Test
    void shouldNotGetEventByName_withoutName() throws Exception {

        when(jwtUtil.isValidToken(EventControllerFixture.JWT)).thenReturn(true);
        when(jwtUtil.authenticate(EventControllerFixture.JWT)).thenReturn(EventControllerFixture.authentication());

        mvc.perform(
                get(URLS.FINAL_EVENT_GET_BY_NAME)
                        .header("Authorization", EventControllerFixture.JWT)
        ).andExpect(status().isBadRequest());

        verify(eventService, never()).getEventByName(any(String.class));
    }

    @Test
    void shouldNotGetEventByName_withoutToken() throws Exception {
        mvc.perform(
                get(URLS.FINAL_EVENT_GET_BY_NAME)
                        .header("Authorization", EventControllerFixture.JWT)
        ).andExpect(status().isForbidden());

        verify(eventService, never()).getEventByName(any(String.class));
    }

    @Test
    void shouldGetEventByDate() throws Exception {

        when(jwtUtil.isValidToken(EventControllerFixture.JWT)).thenReturn(true);
        when(jwtUtil.authenticate(EventControllerFixture.JWT)).thenReturn(EventControllerFixture.authentication());

        when(eventService.getEventByDate(EventControllerFixture.HEADER_DATE))
                .thenReturn(EventControllerFixture.eventGetByDateResponse());

        var result = mvc.perform(
                get(URLS.FINAL_EVENT_GET_BY_DATE)
                        .header("Authorization", EventControllerFixture.JWT)
                        .header("date", EventControllerFixture.HEADER_DATE.toString())
        ).andExpect(status().isOk()).andReturn();

        verify(eventService, times(1)).getEventByDate(any(LocalDateTime.class));
        assertEquals(FileUtils.readFileFromClassLoader("json/expected/event-get-date-expected.json"), result.getResponse().getContentAsString());
    }

    @Test
    void shouldNotGetEventByDate_withoutDate() throws Exception {

        when(jwtUtil.isValidToken(EventControllerFixture.JWT)).thenReturn(true);
        when(jwtUtil.authenticate(EventControllerFixture.JWT)).thenReturn(EventControllerFixture.authentication());

        mvc.perform(
                get(URLS.FINAL_EVENT_GET_BY_DATE)
                        .header("Authorization", EventControllerFixture.JWT)
        ).andExpect(status().isBadRequest());

        verify(eventService, never()).getEventByDate(any(LocalDateTime.class));
    }

    @Test
    void shouldNotGetEventByDate_withoutToken() throws Exception {
        mvc.perform(
                get(URLS.FINAL_EVENT_GET_BY_DATE)
                        .header("Authorization", EventControllerFixture.JWT)
        ).andExpect(status().isForbidden());

        verify(eventService, never()).getEventByDate(any(LocalDateTime.class));
    }






}
