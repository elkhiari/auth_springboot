package com.eclipse.blogging.transformers;

import com.eclipse.blogging.dto.UserDto;
import com.eclipse.blogging.entities.User;

import java.util.stream.Collectors;

public class UserTransformer {
    public static UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .lastname(user.getLastname())
                .firstname(user.getFirstname())
                .build();
    }

    public static UserDto toDtoWithoutPost(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .lastname(user.getLastname())
                .firstname(user.getFirstname())
                .build();
    }
}
