package org.shivam.codemeetly.controller;

import lombok.RequiredArgsConstructor;
import org.shivam.codemeetly.dto.LoginRequest;
import org.shivam.codemeetly.dto.RegisterRequest;
import org.shivam.codemeetly.service.AuthService;
import org.shivam.codemeetly.service.GoogleOAuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final GoogleOAuthService googleAuth;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {

        return authService.register(
                request.getName(),
                request.getEmail(),
                request.getPassword(),
                request.getRole()
        );
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        return authService.login(
                request.getEmail(),
                request.getPassword()
        );
    }

    @PostMapping("/google")
    public String googleLogin(@RequestParam String idToken) {
        return googleAuth.loginWithGoogle(idToken);
    }
}