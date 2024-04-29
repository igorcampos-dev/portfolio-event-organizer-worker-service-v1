package com.io.java.events.managers.infrastructure.entity;

import com.io.java.events.managers.application.dto.request.EventPutRequestDto;
import com.io.java.events.managers.application.dto.request.EventRequestDto;
import com.io.java.events.managers.application.dto.request.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "event_entity", indexes = {
        @Index(name = "id_idx", columnList = "id", unique = true),
        @Index(name = "name_idx", columnList = "name", unique = true),
        @Index(name = "date_idx", columnList = "date"),
})
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true)
    private String id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;


    public static EventEntity buildClass(EventPutRequestDto eventRequestDto){
        return EventEntity.builder()
                .id(eventRequestDto.id())
                .date(eventRequestDto.eventData())
                .status(eventRequestDto.status())
                .description(eventRequestDto.eventDescription())
                .name(eventRequestDto.eventName())
                .build();
    }

    public static EventEntity buildClassWithoutId(EventRequestDto eventRequestDto){
       return EventEntity.builder()
                .date(eventRequestDto.eventData())
                .status(eventRequestDto.status())
                .description(eventRequestDto.eventDescription())
                .name(eventRequestDto.eventName())
                .build();
    }
}
