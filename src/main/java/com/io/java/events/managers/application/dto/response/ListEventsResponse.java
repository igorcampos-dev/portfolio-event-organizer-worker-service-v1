package com.io.java.events.managers.application.dto.response;

import com.io.java.events.managers.application.dto.request.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListEventsResponse {
    private String id;
    private String eventDescription;
    private String eventName;
    private LocalDateTime eventData;
    private Status status;
}
