package com.microservice.comment.serviceImpl;

import com.microservice.comment.Config.RestTemplateConfig;
import com.microservice.comment.entity.Comment;
import com.microservice.comment.exception.ResourceNotFoundException;
import com.microservice.comment.payload.CommentDto;
import com.microservice.comment.payload.Post;
import com.microservice.comment.repository.CommentRepository;
import com.microservice.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private RestTemplateConfig restTemplate;

    @Autowired
    private CommentRepository commentRepository;

    public CommentDto saveComment(CommentDto dto) {

        CommentDto commentDto=null;
            Post post = restTemplate.getRestTemplate().getForObject("http://POST-SERVICE/api/posts/PostId/" + dto.getPostId(), Post.class);
            if (post != null) {

                Comment comment = new Comment();

                String commentId = UUID.randomUUID().toString();
                comment.setName(dto.getName());
                comment.setBody(dto.getBody());
                comment.setEmail(dto.getEmail());
                comment.setPostId(dto.getPostId());
                comment.setCommentId(commentId);

                Comment saved = commentRepository.save(comment);

                commentDto=new CommentDto();
                commentDto.setName(saved.getName());
                commentDto.setBody(saved.getBody());
                commentDto.setCommentId(saved.getCommentId());
                commentDto.setPostId(saved.getPostId());
                commentDto.setEmail(saved.getEmail());
                return commentDto;
            } else {
                throw new  ResourceNotFoundException("Post not found with id:" + commentDto.getPostId());
            }
    }

    @Override
    public List<CommentDto> getAllCommentsByPostId(String postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        List<CommentDto> commentDtoList = comments.stream().map((p) -> entityToDto(p)).collect(Collectors.toList());
        return commentDtoList;

    }






    CommentDto entityToDto(Comment comment ){
        CommentDto commentDto=new CommentDto();
        commentDto.setEmail(comment.getEmail());
        commentDto.setName(comment.getName());
        commentDto.setPostId(comment.getPostId());
        commentDto.setCommentId(comment.getCommentId());
        commentDto.setBody(comment.getBody());
        return commentDto;
    }
}
