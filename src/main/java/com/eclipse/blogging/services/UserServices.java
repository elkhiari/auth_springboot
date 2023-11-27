package com.eclipse.blogging.services;

import com.eclipse.blogging.dto.UserDto;
import com.eclipse.blogging.entities.User;
import com.eclipse.blogging.repositories.UserRepository;
import com.eclipse.blogging.transformers.UserTransformer;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServices {
    private final UserRepository userRepository;

    public User getMe() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return userRepository.findById(user.getId()).orElseThrow();
    }

    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(UserTransformer::toDtoWithoutPost).toList();
    }

}
