package com.eclipse.blogging.services;

import com.eclipse.blogging.config.JwtService;
import com.eclipse.blogging.controllers.auth.AuthentificationResponse;
import com.eclipse.blogging.controllers.auth.LoginRequest;
import com.eclipse.blogging.controllers.auth.RegisterRequest;
import com.eclipse.blogging.entities.User;
import com.eclipse.blogging.enums.Role;
import com.eclipse.blogging.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServices {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthentificationResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthentificationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthentificationResponse register(RegisterRequest request) {
        var user = User.builder()
                .lastname(request.getLastname())
                .firstname(request.getFirstname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthentificationResponse.builder()
                .token(jwtToken)
                .build();
    }

}
