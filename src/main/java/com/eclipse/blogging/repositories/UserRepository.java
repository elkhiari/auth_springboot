package com.eclipse.blogging.repositories;

import com.eclipse.blogging.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User,Long> {
    Optional<User> findByEmail(String email);
}
