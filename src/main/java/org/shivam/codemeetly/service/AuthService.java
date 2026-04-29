package org.shivam.codemeetly.service;

import org.shivam.codemeetly.entity.Role;

public interface AuthService {

    String register(String name, String email, String password, String role);

    String login(String email, String password);

    String generateToken(String email, Role role);
}