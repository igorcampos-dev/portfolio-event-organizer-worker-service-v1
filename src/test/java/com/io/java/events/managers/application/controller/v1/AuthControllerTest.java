package com.io.java.events.managers.application.controller.v1;

import com.io.java.events.managers.application.config.TestConfig;
import com.io.java.events.managers.application.dto.request.UserRequestDto;
import com.io.java.events.managers.application.utils.FileUtils;
import com.io.java.events.managers.application.controller.v1.fixture.AuthControllerFixture;
import com.io.java.events.managers.application.utils.URLS;
import com.io.java.events.managers.domain.service.UserService;
import com.io.java.events.managers.security.config.SecurityConfig;
import com.io.java.events.managers.security.util.JwtImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@Import({AuthController.class, SecurityConfig.class})
@ContextConfiguration(classes = TestConfig.class)
public class AuthControllerTest {


    @Autowired
    @SuppressWarnings("unused")
    private MockMvc mvc;

    @MockBean
    @SuppressWarnings("unused")
    private UserService userService;

    @MockBean
    @SuppressWarnings("unused")
    private JwtImpl jwtUtil;

    @Test
    void shouldLogin() throws Exception {

        when(userService.loginUser(AuthControllerFixture.userRequestDto()))
                .thenReturn(AuthControllerFixture.userResponse());

        var result = mvc.perform(
                post(URLS.FINAL_AUTH_LOGIN)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(FileUtils.readFileFromClassLoader("json/request/login-request.json"))
        ).andExpect(status().isOk()).andReturn();

        verify(userService, times(1)).loginUser(any(UserRequestDto.class));
        assertEquals(FileUtils.readFileFromClassLoader("json/expected/login-expected.json"), result.getResponse().getContentAsString());

    }

    @Test
    void shouldNotLogin_withoutPassword() throws Exception {

        mvc.perform(
                post(URLS.FINAL_AUTH_LOGIN)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(FileUtils.readFileFromClassLoader("json/request/login-request-error-without-password.json"))
        ).andExpect(status().isBadRequest()).andReturn();

        verify(userService, never()).loginUser(any(UserRequestDto.class));

    }

    @Test
    void shouldNotLogin_withoutEmail() throws Exception {

        mvc.perform(
                post(URLS.FINAL_AUTH_LOGIN)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(FileUtils.readFileFromClassLoader("json/request/login-request-error-without-email.json"))
        ).andExpect(status().isBadRequest()).andReturn();

        verify(userService, never()).loginUser(any(UserRequestDto.class));

    }

}
