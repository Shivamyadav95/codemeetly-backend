package org.shivam.codemeetly.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.shivam.codemeetly.dto.*;
import org.shivam.codemeetly.entity.User;
import org.shivam.codemeetly.entity.Role;
import org.shivam.codemeetly.repository.UserRepository;
import org.shivam.codemeetly.service.AuthService;
import org.shivam.codemeetly.util.JwtService; // ✅ IMPORTANT

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;
    private final JwtService jwtService; // ✅ ADD THIS

    // ✅ REGISTER
    @PostMapping("/register")
    public ApiResponse<String> register(@RequestBody AuthRequest request) {

        String result = authService.register(
                request.getName(),
                request.getEmail(),
                request.getPassword(),
                request.getRole()
        );

        return new ApiResponse<>("User registered successfully", result);
    }

    // ✅ LOGIN
    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@RequestBody AuthRequest request) {

        String token = authService.login(
                request.getEmail(),
                request.getPassword()
        );

        return new ApiResponse<>("Login successful", new AuthResponse(token));
    }

    // ✅ GET CURRENT USER
    @GetMapping("/me")
    public ApiResponse<UserResponse> getCurrentUser(Authentication authentication) {

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole().name());

        return new ApiResponse<>("User fetched successfully", response);
    }

    // 🔥 GOOGLE LOGIN FIXED
    @GetMapping("/oauth-success")
    public void oauthSuccess(HttpServletResponse response, Authentication authentication) throws IOException {

        OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();

        String email = oauthUser.getAttribute("email");
        String name = oauthUser.getAttribute("name");

        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            user = User.builder()
                    .email(email)
                    .fullName(name)
                    .provider("GOOGLE")
                    .role(Role.USER)
                    .build();

            userRepository.save(user);
        }

        // ✅ FIXED: USE JwtService (NOT AuthService)
        String token = jwtService.generateToken(user.getEmail());

        // ✅ REDIRECT TO FRONTEND
        response.sendRedirect("http://localhost:5173/oauth2/redirect?token=" + token);
    }
}