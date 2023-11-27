package com.eclipse.blogging.transformers;

import com.eclipse.blogging.dto.PostDto;
import com.eclipse.blogging.entities.Post;

public class PostTransformer {
    public static PostDto toDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .content(post.getContent())
                .comment(post.getComments().stream().map(CommentTransformer::toDto).toList())
                .publisher(UserTransformer.toDtoWithoutPost(post.getPublisher()))
                .createAt(post.getCreatedAt())
                .build();
    }

    public static PostDto toDtoWithoutUser(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .publisher(null)
                .content(post.getContent())
                .createAt(post.getCreatedAt())
                .build();
    }
}
