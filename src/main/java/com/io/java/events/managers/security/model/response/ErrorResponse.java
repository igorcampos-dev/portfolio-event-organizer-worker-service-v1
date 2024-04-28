package com.io.java.events.managers.security.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import java.io.IOException;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;

    public ErrorResponse(String message) {
        timestamp = LocalDateTime.now();
        this.message = message;
    }

    public static void getErrorUnauthorized(HttpServletResponse response){
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        buildResponse(response, "Unauthenticated request.");
    }

    public static void getError(HttpServletResponse response, Exception e){
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        buildResponse(response, e.getMessage());
    }


    @SneakyThrows(IOException.class)
    private static void buildResponse(HttpServletResponse response, String message){
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.writeValue(response.getWriter() ,new ErrorResponse(message));
    }
}
