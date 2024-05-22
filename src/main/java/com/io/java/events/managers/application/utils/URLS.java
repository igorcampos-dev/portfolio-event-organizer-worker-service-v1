package com.io.java.events.managers.application.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpMethod;
import java.util.List;

@Data
@AllArgsConstructor
public class URLS {

    private String name;
    private HttpMethod httpMethod;

    public static final String EVENT_PATH = "/v1/events";
    public static final String AUTH_PATH = "/v1/auth";
    public static final String EVENT_GET_BY_NAME = "/name";
    public static final String EVENT_GET_BY_DATE = "/date";
    public static final String AUTH_LOGIN = "/login";
    public static final String FINAL_EVENT_GET_BY_NAME = EVENT_PATH.concat(EVENT_GET_BY_NAME);
    public static final String FINAL_EVENT_GET_BY_DATE = EVENT_PATH.concat(EVENT_GET_BY_DATE);
    public static final String FINAL_AUTH_LOGIN = AUTH_PATH.concat(AUTH_LOGIN);

    public static List<URLS> getRhRoutes(){
        return List.of(
                new URLS(EVENT_PATH, HttpMethod.POST),
                new URLS(EVENT_PATH, HttpMethod.PUT),
                new URLS(FINAL_EVENT_GET_BY_DATE, HttpMethod.GET),
                new URLS(FINAL_EVENT_GET_BY_NAME, HttpMethod.GET)
        );
    }

    public static List<URLS> getPublicRoutes(){
        return List.of(
                new URLS(FINAL_AUTH_LOGIN, HttpMethod.POST),
                new URLS("/swagger-ui.html", HttpMethod.GET),
                new URLS("/swagger-ui/swagger-initializer.js", HttpMethod.GET),
                new URLS("/swagger-ui/swagger-ui-standalone-preset.js", HttpMethod.GET),
                new URLS("/swagger-ui/swagger-ui.css", HttpMethod.GET),
                new URLS("/swagger-ui/swagger-ui-bundle.js", HttpMethod.GET),
                new URLS("/swagger-ui/favicon-32x32.png", HttpMethod.GET),
                new URLS("/v3/api-docs", HttpMethod.GET),
                new URLS("/v3/api-docs/swagger-config", HttpMethod.GET),
                new URLS("/swagger-ui/favicon-16x16.png", HttpMethod.GET),
                new URLS("/swagger-ui/index.css", HttpMethod.GET),
                new URLS("/swagger-ui/index.html", HttpMethod.GET)
        );
    }
}

