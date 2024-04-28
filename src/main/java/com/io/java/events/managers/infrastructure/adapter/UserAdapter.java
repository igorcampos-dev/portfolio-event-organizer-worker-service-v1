package com.io.java.events.managers.infrastructure.adapter;

import com.io.java.events.managers.application.dto.request.UserRequestDto;
import com.io.java.events.managers.application.dto.response.UserResponse;
import com.io.java.events.managers.application.dto.response.field.TokenType;
import com.io.java.events.managers.domain.persistence.UserPersistence;
import com.io.java.events.managers.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAdapter implements UserPersistence {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public UserResponse loginUser(UserRequestDto userRequestDto) {
        var user = this.authenticate(userRequestDto);
        String token = this.jwtUtil.encode(user);
        return UserResponse.builder()
                .access_token(token)
                .expires_in(86400)
                .token_type(TokenType.BEARER.toString())
                .build();
    }


    private UserDetails authenticate(UserRequestDto userRequestDto){
        var usernamePassword = new UsernamePasswordAuthenticationToken(userRequestDto.email(), userRequestDto.password());
        return (UserDetails) this.authenticationManager.authenticate(usernamePassword).getPrincipal();
    }

}
