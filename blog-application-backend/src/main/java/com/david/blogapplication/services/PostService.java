package com.david.blogapplication.services;

import com.david.blogapplication.entities.Post;
import com.david.blogapplication.payloads.PostDto;

import java.util.List;

public interface PostService {
    //create
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    //update
    Post updatePost(PostDto postDto, Integer postId);

    //delete
    void deletePost(Integer postId);

    //get
    Post getPostById(Integer postId);

    //get all
    List<Post> getAllPost();

    //get all post by category
    List<Post> getPostsByCategory(Integer categoryId);

    //get all post by user
    List<Post> getPostsByUser(Integer userId);

    //search posts
    List<Post> searchPosts(String keyword);

}
