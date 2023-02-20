package com.david.blogapplication.controllers;

import com.david.blogapplication.entities.Post;
import com.david.blogapplication.payloads.ApiResponse;
import com.david.blogapplication.payloads.PostDto;
import com.david.blogapplication.payloads.PostResponse;
import com.david.blogapplication.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    PostService postService;

    //create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId){
        PostDto newPostDto = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(newPostDto, HttpStatus.CREATED);
    }

    //get all post
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber, @RequestParam(value="pageSize", defaultValue = "5", required = false) Integer pageSize){
        PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize);
        return ResponseEntity.ok(postResponse);
    }

    //get post by id
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
        PostDto postById = this.postService.getPostById(postId);
        return ResponseEntity.ok(postById);
    }

    //get posts by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
        List<PostDto> postsByUser = this.postService.getPostsByUser(userId);
        return ResponseEntity.ok(postsByUser);
    }

    //get posts by category
    @GetMapping("category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
        List<PostDto> postsByCategory = this.postService.getPostsByCategory(categoryId);
        return ResponseEntity.ok(postsByCategory);
    }

    //delete post by Id
    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ApiResponse("Post is successfully deleted !!", true);
    }

    //update post by Id
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId){
        PostDto updatePost = this.postService.updatePost(postDto, postId);
        return ResponseEntity.ok(updatePost);
    }
}
