package com.io.java.events.managers.domain.service.fixture;

import com.io.java.events.managers.application.dto.request.UserRequestDto;
import com.io.java.events.managers.application.dto.response.UserResponse;
import com.io.java.events.managers.application.dto.response.field.TokenType;

public class UserServiceFixture {

    public static UserRequestDto userRequest() {
        return UserRequestDto.builder()
                .email("Test@gmail.com")
                .password("Test pass")
                .build();
    }

    public static UserResponse userResponse() {
        return UserResponse.builder()
                .access_token("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c")
                .expires_in(3600)
                .token_type(TokenType.BEARER.toString())
                .build();
    }

}
