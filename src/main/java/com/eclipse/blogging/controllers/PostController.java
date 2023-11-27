package com.eclipse.blogging.controllers;

import com.eclipse.blogging.controllers.post.PostRequest;
import com.eclipse.blogging.dto.PostDto;
import com.eclipse.blogging.entities.Post;
import com.eclipse.blogging.services.PostServices;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostServices postServices;
    @PostMapping(value = {"/","","/add"})
    public ResponseEntity<PostDto> addPost(@Valid @RequestBody PostRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postServices.addPost(request));
    }

    @GetMapping(value = {"/","/all",""})
    public ResponseEntity<List<PostDto>> getAll() {
        return ResponseEntity.ok().body(postServices.getAllPost());
    }
}
