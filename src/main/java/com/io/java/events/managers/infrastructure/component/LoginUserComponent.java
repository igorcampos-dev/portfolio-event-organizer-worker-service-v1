package com.io.java.events.managers.infrastructure.component;

import com.io.java.events.managers.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginUserComponent implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username){
        return this.findByEmail(username);
    }

    public UserDetails findByEmail(String username) {
        var user = this.userRepository.findByEmailOrElseThrow(username);
        return new User(user.getEmail(), null , user.getAuthorities());
    }
}