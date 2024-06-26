package com.io.java.events.managers.infrastructure.adapter;

import com.io.java.events.managers.application.dto.request.UserRequestDto;
import com.io.java.events.managers.application.dto.response.UserResponse;
import com.io.java.events.managers.infrastructure.adapter.fixture.UserAdapterFixture;
import com.io.java.events.managers.security.util.JwtImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserAdapterTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtImpl jwtUtil;

    @InjectMocks
    private UserAdapter userAdapter;

    @Test
    void shouldLoginUser() {

        UserRequestDto userRequestDto = UserAdapterFixture.newUserRequestDto();
        UserDetails mockUserDetails = UserAdapterFixture.userDetails();
        Authentication mockAuthentication = mock(Authentication.class);

        when(mockAuthentication.getPrincipal())
                .thenReturn(mockUserDetails);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(mockAuthentication);

        when(jwtUtil.encode(mockUserDetails))
                .thenReturn(UserAdapterFixture.TOKEN);

        UserResponse userResponse = userAdapter.loginUser(userRequestDto);

        verify(jwtUtil, times(1)).encode(any(UserDetails.class));
        assertNotNull(userResponse);
        assertNotNull(userResponse.getAccess_token());
        assertNotNull(userResponse.getExpires_in());
        assertNotNull(userResponse.getToken_type());

    }
}
