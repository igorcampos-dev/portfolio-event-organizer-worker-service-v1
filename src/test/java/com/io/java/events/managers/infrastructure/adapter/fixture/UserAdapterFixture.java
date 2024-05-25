package com.io.java.events.managers.infrastructure.adapter.fixture;

import com.io.java.events.managers.application.dto.request.UserRequestDto;
import com.io.java.events.managers.infrastructure.entity.UsersEntity;
import com.io.java.events.managers.infrastructure.entity.field.Role;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

public class UserAdapterFixture {

    public static final String TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhcGktYXV0aCIsInVzZXJuYW1lIjoicmguZXhhbXBsZUBnbWFpbC5jb20iLCJyb2xlcyI6WyJST0xFQ3JIIl0sImV4cCI6MTcxNTY9MTQ2OH0.t4u_ztmpz5VD6tY99KcTJ_d1YeTdcpPAP6ycTTBxp3s";

    public static UserRequestDto newUserRequestDto() {
        return UserRequestDto.builder()
                .email("Test@gmail.com")
                .password("Test pass")
                .build();
    }

    public static UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(newUserRequestDto().email(), newUserRequestDto().password());
    }

    public static UserDetails userDetails() {
        return UsersEntity.builder()
                .id(1L)
                .name("Test name")
                .role(Role.ADMIN)
                .email("Test@gmail.com")
                .password("TEST PASS")
                .build();
    }
}
