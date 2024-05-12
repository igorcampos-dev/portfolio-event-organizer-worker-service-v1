package com.io.java.events.managers.infrastructure.entity;

import com.io.java.events.managers.application.dto.request.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_EVENT", indexes = {
        @Index(name = "IDX_ID", columnList = "PK_ID", unique = true),
        @Index(name = "IDX_NAME", columnList = "EVT_ST_NAME", unique = true),
        @Index(name = "IDX_DATE", columnList = "EVT_DT_DATE"),
})
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "PK_ID", unique = true)
    @Comment("Id único de um evento")
    private String id;

    @Column(name = "EVT_ST_NAME", unique = true)
    @Comment("Nome de um evento")
    private String name;

    @Column(name = "EVT_DT_DATE")
    @Comment("Data do evento")
    private LocalDateTime date;

    @Column(name = "EVT_ST_DESCRIPTION")
    @Comment("Descrição do evento")
    private String description;

    @Column(name = "EVT_ST_STATUS")
    @Enumerated(EnumType.STRING)
    @Comment("Status do evento")
    private Status status;

}
