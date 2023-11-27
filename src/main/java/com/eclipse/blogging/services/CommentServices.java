package com.eclipse.blogging.services;

import com.eclipse.blogging.controllers.comment.CommentRequest;
import com.eclipse.blogging.dto.CommentDto;
import com.eclipse.blogging.entities.Comment;
import com.eclipse.blogging.entities.Post;
import com.eclipse.blogging.entities.User;
import com.eclipse.blogging.exception.NotFoundException;
import com.eclipse.blogging.repositories.CommentRepository;
import com.eclipse.blogging.repositories.PostRepository;
import com.eclipse.blogging.transformers.CommentTransformer;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class CommentServices {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public CommentDto addComment(CommentRequest request, Long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new NotFoundException("Post not found"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        Comment comment = Comment.builder()
                .user(user)
                .post(post)
                .commentAt(new Date())
                .content(request.getContent())
                .build();

        return CommentTransformer.toDto(commentRepository.save(comment));
    }
}
