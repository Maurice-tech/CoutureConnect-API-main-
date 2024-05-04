package com.fashion.Blog.dto;

import com.fashion.Blog.model.Post;
import com.fashion.Blog.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private String comment;
    private Timestamp createdAt;
    private Long id;
    private User user;
    private Post post;
}
