package com.io.java.events.managers.application.utils;

import lombok.SneakyThrows;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Objects {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public static <T> void requireNonEmptyOrNull(T obj, String message) {
        if (obj.toString().isEmpty() || obj.toString() == null)
            throw new NullPointerException(message);
    }

    @SneakyThrows
    public static LocalDateTime validateFormattedData(String dataString) {
        try {
            return LocalDateTime.parse(dataString, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de data inválido. O formato correto é yyyy-MM-dd'T'HH:mm");
        }
    }
}
