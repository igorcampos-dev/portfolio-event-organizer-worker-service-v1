package com.io.java.events.managers.domain.persistence;

import com.io.java.events.managers.application.dto.request.UserRequestDto;
import com.io.java.events.managers.application.dto.response.UserResponse;

public interface UserPersistence {
    UserResponse loginUser(UserRequestDto userRequestDto);
}
