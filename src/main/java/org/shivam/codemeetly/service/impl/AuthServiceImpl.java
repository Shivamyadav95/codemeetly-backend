package org.shivam.codemeetly.service.impl;

import lombok.RequiredArgsConstructor;

import org.shivam.codemeetly.entity.User;
import org.shivam.codemeetly.entity.Role;
import org.shivam.codemeetly.repository.UserRepository;
import org.shivam.codemeetly.service.AuthService;
import org.shivam.codemeetly.util.JwtService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    // ✅ REGISTER
    @Override
    public String register(String name, String email, String password, String role) {

        User user = new User();
        user.setFullName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(Role.valueOf(role));

        userRepository.save(user);

        return "User Registered Successfully";
    }

    // ✅ LOGIN
    @Override
    public String login(String email, String password) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        return jwtService.generateToken(email);
    }

    // ✅ GENERATE TOKEN
    @Override
    public String generateToken(String email, Role role) {
        return jwtService.generateToken(email);
    }
}