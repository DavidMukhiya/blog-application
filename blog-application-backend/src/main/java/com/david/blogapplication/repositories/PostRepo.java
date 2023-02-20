package com.david.blogapplication.repositories;

import com.david.blogapplication.entities.Category;
import com.david.blogapplication.entities.Post;
import com.david.blogapplication.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
}
