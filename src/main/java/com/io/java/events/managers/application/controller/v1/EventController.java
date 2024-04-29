package com.io.java.events.managers.application.controller.v1;

import com.io.java.events.managers.application.dto.request.EventPutRequestDto;
import com.io.java.events.managers.application.dto.request.EventRequestDto;
import com.io.java.events.managers.application.dto.response.EventResponse;
import com.io.java.events.managers.application.dto.response.EventResponseGet;
import com.io.java.events.managers.application.dto.response.EventsResponse;
import com.io.java.events.managers.application.utils.Objects;
import com.io.java.events.managers.application.utils.URLS;
import com.io.java.events.managers.domain.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@SuppressWarnings("unused")
@RequestMapping(value = URLS.EVENT_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Eventos", description = "Crud de eventos")
public class EventController {

    private final EventService eventService;

    @ApiResponse(description = "Salva um evento", responseCode = "201")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"), summary = "Salva um evento no banco de dados", description = """
            # Envia um evento pelo corpo da mensagem e adicionamos ele no banco de dados
            ---
           
            """)
    @PostMapping
    public ResponseEntity<EventResponse> saveEvent(@RequestBody @Valid EventRequestDto eventRequestDto){
        var response = eventService.saveEvent(eventRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ApiResponse(description = "Atualiza um evento", responseCode = "200")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"), summary = "Atualiza um evento no banco de dados", description = """
            # Envia um evento pelo corpo da mensagem e atualiza o mesmo no banco de dados
            ---
           
            """)
    @PutMapping
    public ResponseEntity<EventResponse> updateEvent(@RequestBody @Valid EventPutRequestDto eventRequestDto){
        var response = eventService.updateEvent(eventRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiResponse(description = "Busca um evento pelo nome", responseCode = "200")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"), summary = "Busca um evento pelo nome do banco de dados", description = """
            # Enviamos por parametro um nome que é um nome de um evento salvo no banco  e retornamos os seus dados.
            ---
           
            """)
    @GetMapping("/name")
    public ResponseEntity<EventsResponse> getEventByName(@RequestHeader("name") String name) {
        Objects.requireNonEmptyOrNull(name, "O cabeçalho name não pode ser vazio ou nulo.");
        var response = eventService.getEventByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiResponse(description = "Busca evento(s) por uma data", responseCode = "200")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"), summary = "Busca evento(s) por uma data no banco de dados", description = """
            # Enviamos por parametro a data que é a data de um evento salvo no banco  e retornamos os seus dados.
            ---
           
            """)
    @GetMapping("/date")
    public ResponseEntity<EventResponseGet> getEventByDate(@RequestHeader("date") String date){
        Objects.requireNonEmptyOrNull(date, "O cabeçalho name não pode ser vazio ou nulo.");
        LocalDateTime newDate = Objects.validateFormattedData(date);
        var response = eventService.getEventByDate(newDate);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
