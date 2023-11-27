package com.eclipse.blogging.transformers;

import com.eclipse.blogging.dto.CommentDto;
import com.eclipse.blogging.entities.Comment;

public class CommentTransformer {

    public static CommentDto toDto(Comment comment) {
        return CommentDto.builder()
                .commentAt(comment.getCommentAt())
                .content(comment.getContent())
                .user(UserTransformer.toDto(comment.getUser()))
                .id(comment.getId())
                .build();
    }
}
