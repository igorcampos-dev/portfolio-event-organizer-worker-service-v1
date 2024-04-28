package com.io.java.events.managers.infrastructure.entity;

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
        @Index(name = "date_idx", columnList = "date", unique = true),
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
}
