package com.eclipse.blogging.controllers;

import com.eclipse.blogging.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class TestController {
    @GetMapping("hello")
    private ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello any one auth");
    }

    @GetMapping("/me")
    public UserDetails getMe() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user;
    }

}
