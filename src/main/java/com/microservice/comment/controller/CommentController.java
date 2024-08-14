package com.microservice.comment.controller;

import com.microservice.comment.payload.CommentDto;
import com.microservice.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDto> saveComment(@RequestBody CommentDto dto){
        CommentDto commentDto=commentService.saveComment(dto);
        return  new ResponseEntity<>(commentDto, HttpStatus.CREATED);
    }

    @GetMapping("/PostId/{postId}")
    public ResponseEntity<List<CommentDto>> getAllCommentsByPostId(@PathVariable String postId){
        List<CommentDto> commentDtoList=commentService.getAllCommentsByPostId(postId);
        return new ResponseEntity<List<CommentDto>>(commentDtoList,HttpStatus.OK);
    }
}
