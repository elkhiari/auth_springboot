package com.eclipse.blogging.controllers;

import com.eclipse.blogging.controllers.auth.AuthentificationResponse;
import com.eclipse.blogging.controllers.auth.LoginRequest;
import com.eclipse.blogging.controllers.auth.RegisterRequest;
import com.eclipse.blogging.entities.User;
import com.eclipse.blogging.services.AuthServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthServices authServices;

    @PostMapping("/register")
    public ResponseEntity<AuthentificationResponse> register(
           @Valid @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authServices.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthentificationResponse> register(
            @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(authServices.login(request));
    }
}
