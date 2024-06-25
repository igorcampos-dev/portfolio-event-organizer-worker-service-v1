package com.io.java.events.managers.application.controller.v1.fixture;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.io.java.events.managers.application.dto.request.EventPutRequestDto;
import com.io.java.events.managers.application.dto.request.EventRequestDto;
import com.io.java.events.managers.application.dto.request.Status;
import com.io.java.events.managers.application.dto.response.EventResponse;
import com.io.java.events.managers.application.dto.response.EventResponseGet;
import com.io.java.events.managers.application.dto.response.EventsResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class EventControllerFixture {


    public static final String JWT = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhcGktYXV0aCIsInVzZXJuYW1lIjoicmguZXhhbXBsZUBnbWFpbC5jb20iLCJyb2xlcyI6WyJPT0xFX1JIIl0sImV4cCI6MTcxNTY1MTQ2OH0.t4u_ztmpz4VD6tY99KcTJ_d1YeTdcpPAP6ycTTBxp3s";
    public static final String HEADER_NAME = "NAME_TEST";
    public static final LocalDateTime HEADER_DATE = LocalDateTime.parse("2024-05-24T10:15", DateTimeFormatter.ISO_LOCAL_DATE_TIME);

    public static EventRequestDto eventRequest() {
        return EventRequestDto.builder()
                .eventData(LocalDateTime.parse("2024-05-24T10:15:30", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .eventDescription("Test description")
                .eventName("Test name")
                .status(Status.ACTIVE)
                .build();
    }

    public static EventResponse eventSuccessResponseSave() {
        return EventResponse.responseStatusSuccessSave();
    }

    public static Authentication authentication() {
        return new UsernamePasswordAuthenticationToken(
                "Test",
                null,
                Arrays.asList(
                        new SimpleGrantedAuthority("ROLE_ADM"),
                        new SimpleGrantedAuthority("ROLE_RH"),
                        new SimpleGrantedAuthority("ROLE_USER")
                )
        );
    }

    public static EventPutRequestDto eventRequestPut() {
        return EventPutRequestDto.builder()
                .id("2f5bdc5d-2b4a-4b6b-bc9d-fa1572c0ab37")
                .eventData(LocalDateTime.parse("2024-05-24T10:15:30", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .eventDescription("Test description")
                .eventName("Test name")
                .status(Status.ACTIVE)
                .build();
    }

    public static EventResponse eventSuccessResponseUpdate(){
        return EventResponse.responseStatusSuccessUpdate();
    }

    public static EventsResponse eventGetByNameResponse() {
        return EventsResponse.builder()
                .id("0a0aaaaa-0a00-aaaa-aaaa-aa0000a0aa00")
                .eventData(LocalDateTime.parse("2024-05-24T10:15:30", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .eventDescription("Test description")
                .eventName("Test name")
                .status(Status.ACTIVE)
                .build();
    }

    public static EventResponseGet eventGetByDateResponse() {
        return EventResponseGet.builder()
                .date(LocalDateTime.parse("2024-05-24T10:15:30", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .events(List.of(eventGetByNameResponse()))
                .build();
    }

    public static final DecodedJWT JWT_DECODE = new DecodedJWT() {
        @Override
        public String getToken() {
            return "";
        }

        @Override
        public String getHeader() {
            return "";
        }

        @Override
        public String getPayload() {
            return "";
        }

        @Override
        public String getSignature() {
            return "";
        }

        @Override
        public String getAlgorithm() {
            return "";
        }

        @Override
        public String getType() {
            return "";
        }

        @Override
        public String getContentType() {
            return "";
        }

        @Override
        public String getKeyId() {
            return "";
        }

        @Override
        public Claim getHeaderClaim(String s) {
            return null;
        }

        @Override
        public String getIssuer() {
            return "";
        }

        @Override
        public String getSubject() {
            return "";
        }

        @Override
        public List<String> getAudience() {
            return List.of();
        }

        @Override
        public Date getExpiresAt() {
            return null;
        }

        @Override
        public Date getNotBefore() {
            return null;
        }

        @Override
        public Date getIssuedAt() {
            return null;
        }

        @Override
        public String getId() {
            return "";
        }

        @Override
        public Claim getClaim(String s) {
            return null;
        }

        @Override
        public Map<String, Claim> getClaims() {
            return Map.of();
        }
    };

}
