package com.io.java.events.managers.security.handler;

import com.io.java.events.managers.security.model.response.ErrorResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.format.DateTimeParseException;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
@SuppressWarnings("unused")
@ApiResponse(description = "Erro ao ler a mensagem HTTP", responseCode = "400")
@ApiResponse(description = "Chave duplicada", responseCode = "400")
@ApiResponse(description = "Valor nulo encontrado", responseCode = "400")
@ApiResponse(description = "Argumento do método inválido", responseCode = "400")
@ApiResponse(description = "Argumento ilegal", responseCode = "400")
@ApiResponse(description = "Formato de data inválido", responseCode = "400")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ExceptionHandlerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorResponse handleHttpMessageNotReadableExceptions(HttpMessageNotReadableException ex) {
        return new ErrorResponse(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateKeyException.class)
    public ErrorResponse handleDuplicateKeyException(DuplicateKeyException ex) {
        return new ErrorResponse(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NullPointerException.class)
    public ErrorResponse handleNullPointerException(NullPointerException ex) {
        return new ErrorResponse(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse methodArgumentInvalidException(MethodArgumentNotValidException ex) {
        String defaultMessage = ex.getBindingResult().getAllErrors().getFirst().getDefaultMessage();
        return new ErrorResponse(defaultMessage);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse methodIllegalArgumentException(IllegalArgumentException ex) {
        return new ErrorResponse(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DateTimeParseException.class)
    public ErrorResponse methodDateTimeParseException(DateTimeParseException ex) {
        String message = "A data passada está inválida, use o formato: ({ano}-{mes}-{dia}T{minutos}:{segundos}) , ex: 2024-04-27T15:30";
        return new ErrorResponse(message);
    }

}
