package org.shivam.codemeetly.service.impl;

import lombok.RequiredArgsConstructor;
import org.shivam.codemeetly.entity.Role;
import org.shivam.codemeetly.entity.User;
import org.shivam.codemeetly.repository.UserRepository;
import org.shivam.codemeetly.service.AuthService;
import org.shivam.codemeetly.util.JwtUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository repo;
    private final JwtUtil jwtUtil;

    @Override
    public String register(String name, String email, String password, String role) {

        if (repo.findByEmail(email).isPresent())
            throw new RuntimeException("User already exists!");

        User user = User.builder()
                .fullName(name)
                .email(email)
                .password(new BCryptPasswordEncoder().encode(password))
                .role(Role.valueOf(role))
                .provider("LOCAL")
                .build();

        repo.save(user);

        return jwtUtil.generateToken(email, user.getRole());
    }

    @Override
    public String login(String email, String password) {

        User user = repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!new BCryptPasswordEncoder().matches(password, user.getPassword()))
            throw new RuntimeException("Invalid credentials");

        return jwtUtil.generateToken(email, user.getRole());
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = repo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword() == null ? "" : user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}