package com.eclipse.blogging.controllers;

import com.eclipse.blogging.dto.UserDto;
import com.eclipse.blogging.entities.User;
import com.eclipse.blogging.services.UserServices;
import com.eclipse.blogging.transformers.UserTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserServices userServices;

//    @GetMapping(value = {"/me","/moi"})
//    public ResponseEntity<User> getMe() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = (User) authentication.getPrincipal();
//        return ResponseEntity.ok(user);
//    }

    @GetMapping(value = {"/me", "/moi"})
    public ResponseEntity<User> getMe() {
        return ResponseEntity.ok(userServices.getMe());
    }

    @GetMapping(value = {"","/","/all"})
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok().body(userServices.getAll());
    }



}
