package com.david.blogapplication.services;

import com.david.blogapplication.entities.Post;
import com.david.blogapplication.payloads.PostDto;

import java.util.List;

public interface PostService {
    //create
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    //update
    PostDto updatePost(PostDto postDto, Integer postId);

    //delete
    void deletePost(Integer postId);

    //get
    PostDto getPostById(Integer postId);

    //get all
    List<PostDto> getAllPost();

    //get all post by category
    List<PostDto> getPostsByCategory(Integer categoryId);

    //get all post by user
    List<PostDto> getPostsByUser(Integer userId);

    //search posts
    List<Post> searchPosts(String keyword);

}
