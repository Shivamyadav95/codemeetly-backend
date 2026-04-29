package org.shivam.codemeetly.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;

import org.shivam.codemeetly.entity.User;
import org.shivam.codemeetly.entity.Role;
import org.shivam.codemeetly.repository.UserRepository;
import org.shivam.codemeetly.util.JwtService;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        // ✅ FIND OR CREATE USER
        User user = userRepository.findByEmail(email)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setEmail(email);
                    newUser.setFullName(name);
                    newUser.setPassword("oauth2user");
                    newUser.setRole(Role.USER);
                    return userRepository.save(newUser);
                });

        // ✅ CORRECT (ONLY EMAIL)
        String token = jwtService.generateToken(user.getEmail());

        // ✅ REDIRECT
        response.sendRedirect("http://localhost:5173/oauth2/redirect?token=" + token);
    }
}