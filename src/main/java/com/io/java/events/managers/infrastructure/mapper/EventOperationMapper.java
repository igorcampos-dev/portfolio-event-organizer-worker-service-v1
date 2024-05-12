package com.io.java.events.managers.infrastructure.mapper;

import com.io.java.events.managers.application.dto.request.EventPutRequestDto;
import com.io.java.events.managers.application.dto.request.EventRequestDto;
import com.io.java.events.managers.application.dto.response.EventsResponse;
import com.io.java.events.managers.infrastructure.entity.EventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class EventOperationMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "eventName", source = "name")
    @Mapping(target = "eventDescription", source = "description")
    @Mapping(target = "eventData", source = "date")
    @Mapping(target = "status", source = "status")
    public abstract EventsResponse toResponse(EventEntity eventEntity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "eventName", source = "name")
    @Mapping(target = "eventDescription", source = "description")
    @Mapping(target = "eventData", source = "date")
    @Mapping(target = "status", source = "status")
    public abstract List<EventsResponse> toResponseList(List<EventEntity> entityList);


    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "eventName")
    @Mapping(target = "date", source = "eventData")
    @Mapping(target = "description", source = "eventDescription")
    @Mapping(target = "status", source = "status")
    public abstract EventEntity toEntity(EventPutRequestDto eventPutRequestDto);

    @Mapping(target = "name", source = "eventName")
    @Mapping(target = "date", source = "eventData")
    @Mapping(target = "description", source = "eventDescription")
    @Mapping(target = "status", source = "status")
    public abstract EventEntity toEntity(EventRequestDto eventRequestDto);


    public List<EventsResponse> toResponseListOrElseThrow(List<EventEntity> entityList) {
        if (entityList == null) {
            throw new IllegalArgumentException("Lista de entidades de evento está nula");
        }
        return toResponseList(entityList);
    }

}
