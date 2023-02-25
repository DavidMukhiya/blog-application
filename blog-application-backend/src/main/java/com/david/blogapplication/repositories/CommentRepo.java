package com.david.blogapplication.repositories;

import com.david.blogapplication.entities.Comment;
import com.david.blogapplication.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
    List<Comment> findByUser(User user);
}
