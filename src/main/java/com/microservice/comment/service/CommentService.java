package com.microservice.comment.service;

import com.microservice.comment.payload.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto saveComment(CommentDto dto);

    List<CommentDto> getAllCommentsByPostId(String postId);
}
