package com.eclipse.blogging.repositories;

import com.eclipse.blogging.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
