package com.david.blogapplication.controllers;

import com.david.blogapplication.config.AppConstants;
import com.david.blogapplication.entities.Post;
import com.david.blogapplication.exceptions.ResourceNotFoundException;
import com.david.blogapplication.payloads.ApiResponse;
import com.david.blogapplication.payloads.PostDto;
import com.david.blogapplication.payloads.PostResponse;
import com.david.blogapplication.services.FileService;
import com.david.blogapplication.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;
    //create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId){
        PostDto newPostDto = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(newPostDto, HttpStatus.CREATED);
    }

    //get all post
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                    @RequestParam(value="pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                    @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
                                                    @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir){
        PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
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

    //search
    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords){
        List<PostDto> result = this.postService.searchPosts(keywords);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image, @PathVariable Integer postId) {
        PostDto updatePost;
        try {
            PostDto postDto = this.postService.getPostById(postId);
            String fileName = this.fileService.uploadImage(path, image);
            postDto.setImageName(fileName);
            updatePost = this.postService.updatePost(postDto, postId);
        } catch (IOException e) {
            throw new ResourceNotFoundException("Image", "Post ID", postId);
        }
        return ResponseEntity.ok(updatePost);
    }
}
