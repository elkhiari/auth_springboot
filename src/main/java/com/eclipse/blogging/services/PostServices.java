package com.eclipse.blogging.services;

import com.eclipse.blogging.controllers.post.PostRequest;
import com.eclipse.blogging.dto.PostDto;
import com.eclipse.blogging.entities.Post;
import com.eclipse.blogging.entities.User;
import com.eclipse.blogging.repositories.PostRepository;
import com.eclipse.blogging.transformers.PostTransformer;
import com.eclipse.blogging.transformers.UserTransformer;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServices {
    private final PostRepository postRepository;

    public PostDto addPost(PostRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Post post = Post.builder()
                .content(request.getContent())
                .publisher(user)
                .createdAt(new Date())
                .build();

        Post newPost = postRepository.save(post);

        return PostDto.builder()
                .id(newPost.getId())
                .publisher(UserTransformer.toDto(newPost.getPublisher()))
                .createAt(newPost.getCreatedAt())
                .content(newPost.getContent())
                .build();
    }

    public List<PostDto> getAllPost() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(PostTransformer::toDto).toList();
    }
}
