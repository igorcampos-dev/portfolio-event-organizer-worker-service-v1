package com.io.java.events.managers.application.dto.response.field;

public enum TokenType {

    BEARER("bearer");

    private final String toString;

    TokenType(String toString) {
        this.toString = toString;
    }

    public String toString() {
        return toString;
    }
}
