package org.shivam.codemeetly.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    String register(String name, String email, String password, String role);
    String login(String email, String password);
}
