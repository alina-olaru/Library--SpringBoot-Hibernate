package com.alina.mylibrary.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtUserDetailsService {
    public UserDetails loadUserByUsername(String username);
}
