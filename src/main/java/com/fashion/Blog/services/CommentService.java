package com.fashion.Blog.services;

import com.fashion.Blog.dto.CommentDto;
import com.fashion.Blog.exceptions.NotFoundException;
import com.fashion.Blog.exceptions.NotNullException;
import com.fashion.Blog.model.ApiMessage;
import com.fashion.Blog.model.Comment;

import java.util.List;

public interface CommentService {
    ApiMessage<Comment> createComment(CommentDto commentDto, Long id) throws NotNullException, NotFoundException;
    ApiMessage<List<Comment>> findAllPostComments(Long id) throws NotFoundException;
    ApiMessage<Comment> updateComment(Long commentId, Comment comment) throws NotNullException;
    ApiMessage<String> deleteCommentById(Long commentId);
}

