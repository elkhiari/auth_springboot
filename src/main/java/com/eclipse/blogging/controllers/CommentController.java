package com.eclipse.blogging.controllers;

import com.eclipse.blogging.controllers.comment.CommentRequest;
import com.eclipse.blogging.dto.CommentDto;
import com.eclipse.blogging.entities.Comment;
import com.eclipse.blogging.services.CommentServices;
import com.eclipse.blogging.services.PostServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {
    private final CommentServices commentServices;
    @PostMapping(value = {"/{id}"})
    private ResponseEntity<CommentDto> addComment(@RequestBody CommentRequest request, @PathVariable Long id) {
        return ResponseEntity.ok().body(commentServices.addComment(request,id));
    }
}
