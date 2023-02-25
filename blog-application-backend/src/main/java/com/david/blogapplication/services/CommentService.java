package com.david.blogapplication.services;

import com.david.blogapplication.payloads.CommentDto;
import org.springframework.stereotype.Service;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, Integer userId, Integer postId);

    void deleteComment(Integer commentId);

}
