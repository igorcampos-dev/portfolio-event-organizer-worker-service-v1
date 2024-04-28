package com.io.java.events.managers.domain.service;

import com.io.java.events.managers.application.dto.request.UserRequestDto;
import com.io.java.events.managers.application.dto.response.UserResponse;

public interface UserService {
    UserResponse loginUser(UserRequestDto userRequestDto);
}
