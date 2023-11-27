package com.eclipse.blogging.dto;


import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CommentDto {
    private Long id;
    private String content;
    private Date commentAt;
    private UserDto user;
}
