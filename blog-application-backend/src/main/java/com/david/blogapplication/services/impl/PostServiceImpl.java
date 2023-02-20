package com.david.blogapplication.services.impl;

import com.david.blogapplication.entities.Category;
import com.david.blogapplication.entities.Post;
import com.david.blogapplication.entities.User;
import com.david.blogapplication.exceptions.ResourceNotFoundException;
import com.david.blogapplication.payloads.PostDto;
import com.david.blogapplication.repositories.CategoryRepo;
import com.david.blogapplication.repositories.PostRepo;
import com.david.blogapplication.repositories.UserRepo;
import com.david.blogapplication.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;


    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User ", " User Id ", userId));
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category ", " Category Id ", categoryId));

        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        this.postRepo.save(post);
        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public Post updatePost(PostDto postDto, Integer postId) {
        return null;
    }

    @Override
    public void deletePost(Integer postId) {

    }

    @Override
    public Post getPostById(Integer postId) {
        return null;
    }

    @Override
    public List<Post> getAllPost() {
        return null;
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category ", " Category Id ", categoryId));
        List<Post> posts = this.postRepo.findByCategory(category);
        List<PostDto> postsByCategory = posts.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postsByCategory;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "User Id", userId));
        List<Post> posts = this.postRepo.findByUser(user);
        List<PostDto> PostsByUser = posts.stream().map((post -> modelMapper.map(post, PostDto.class))).collect(Collectors.toList());
        return PostsByUser;
    }

    @Override
    public List<Post> searchPosts(String keyword) {
        return null;
    }
}
