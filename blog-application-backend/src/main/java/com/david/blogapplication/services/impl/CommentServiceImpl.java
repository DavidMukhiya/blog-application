package com.david.blogapplication.services.impl;

import com.david.blogapplication.entities.Comment;
import com.david.blogapplication.entities.Post;
import com.david.blogapplication.exceptions.ResourceNotFoundException;
import com.david.blogapplication.payloads.CommentDto;
import com.david.blogapplication.repositories.CommentRepo;
import com.david.blogapplication.repositories.PostRepo;
import com.david.blogapplication.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

public class CommentServiceImpl implements CommentService {

    @Autowired
    PostRepo postRepo;

    @Autowired
    CommentRepo commentRepo;

    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post ", " Post Id ", postId));
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment savedComment = this.commentRepo.save(comment);

        return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment ", "Comment Id", commentId));
        this.commentRepo.delete(comment);
    }
}
