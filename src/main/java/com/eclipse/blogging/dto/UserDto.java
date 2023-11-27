package com.eclipse.blogging.dto;

import com.eclipse.blogging.entities.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
}
