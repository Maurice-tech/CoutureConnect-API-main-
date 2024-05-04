package com.fashion.Blog.dto;

import com.fashion.Blog.model.Post;
import com.fashion.Blog.model.User;
import lombok.Data;

@Data
public class LikeAPostDto {
    private Long noOfLikes = 0L;
    private User user;
    private Post post;
}