package com.io.java.events.managers.domain.service;

import com.io.java.events.managers.domain.persistence.UserPersistence;
import com.io.java.events.managers.domain.service.fixture.UserServiceFixture;
import com.io.java.events.managers.domain.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserPersistence userPersistence;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void shouldLoginUser(){
        when(userPersistence.loginUser(UserServiceFixture.userRequest()))
                .thenReturn(UserServiceFixture.userResponse());

        var result = userService.loginUser(UserServiceFixture.userRequest());
        assertEquals(result, UserServiceFixture.userResponse());
    }

}
