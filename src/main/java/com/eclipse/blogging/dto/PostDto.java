package com.eclipse.blogging.dto;

import com.eclipse.blogging.entities.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {
    private Long id;
    private String content;
    private Date createAt;
    private UserDto publisher;
    private List<CommentDto> comment;
}
