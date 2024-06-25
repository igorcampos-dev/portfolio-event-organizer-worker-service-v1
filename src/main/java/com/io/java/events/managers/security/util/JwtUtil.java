package com.io.java.events.managers.security.util;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtUtil {
    void authenticate(String jwt);
    String encode(UserDetails userDetails);
}
