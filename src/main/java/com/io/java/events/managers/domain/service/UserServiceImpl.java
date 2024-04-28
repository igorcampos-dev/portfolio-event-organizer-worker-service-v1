package com.io.java.events.managers.domain.service;

import com.io.java.events.managers.application.dto.request.UserRequestDto;
import com.io.java.events.managers.application.dto.response.UserResponse;
import com.io.java.events.managers.domain.persistence.UserPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserPersistence userPersistence;

    @Override
    public UserResponse loginUser(UserRequestDto userRequestDto) {
        return userPersistence.loginUser(userRequestDto);
    }
}
