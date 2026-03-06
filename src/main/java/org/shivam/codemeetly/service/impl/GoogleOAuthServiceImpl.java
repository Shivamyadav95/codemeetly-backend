package org.shivam.codemeetly.service.impl;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

import lombok.RequiredArgsConstructor;
import org.shivam.codemeetly.entity.Role;
import org.shivam.codemeetly.entity.User;
import org.shivam.codemeetly.repository.UserRepository;
import org.shivam.codemeetly.service.GoogleOAuthService;
import org.shivam.codemeetly.util.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class GoogleOAuthServiceImpl implements GoogleOAuthService {

    private final UserRepository repo;
    private final JwtUtil jwtUtil;

    @Value("${google.client.id}")
    private String googleClientId;

    @Override
    public String loginWithGoogle(String idToken) {

        Payload payload = verifyToken(idToken);

        if (payload == null)
            throw new RuntimeException("Invalid Google token");

        String email = payload.getEmail();
        String name = (String) payload.get("name");
        String picture = (String) payload.get("picture");

        User user = repo.findByEmail(email).orElse(null);

        if (user == null) {
            user = User.builder()
                    .email(email)
                    .fullName(name)
                    .profilePic(picture)
                    .provider("GOOGLE")
                    .role(Role.CANDIDATE)
                    .build();

            repo.save(user);
        }

        return jwtUtil.generateToken(email, user.getRole());
    }

    private Payload verifyToken(String idToken) {
        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                    new NetHttpTransport(),
                    GsonFactory.getDefaultInstance()
            ).setAudience(Collections.singletonList(googleClientId))
                    .build();

            GoogleIdToken token = verifier.verify(idToken);
            return token != null ? token.getPayload() : null;

        } catch (Exception e) {
            return null;
        }
    }
}