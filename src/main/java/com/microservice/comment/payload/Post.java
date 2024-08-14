package com.microservice.comment.payload;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Post {

    private String postId;

    private String title;

    private String description;

    private String content;


}
