package com.fashion.Blog.controllers;

import com.fashion.Blog.dto.CommentDto;
import com.fashion.Blog.exceptions.NotFoundException;
import com.fashion.Blog.exceptions.NotNullException;
import com.fashion.Blog.model.ApiMessage;
import com.fashion.Blog.model.Comment;
import com.fashion.Blog.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/fashion-blog/post")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/create-comment/{postId}")
    public ResponseEntity<ApiMessage<Comment>> createNewComment(@PathVariable Long postId, @RequestBody CommentDto commentDto) throws NotFoundException, NotNullException {
        ApiMessage<Comment> comment = commentService.createComment(commentDto, postId);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @GetMapping("/all-comments/{postId}")
    public ResponseEntity<ApiMessage<List<Comment>>> getAllPostComments(@PathVariable Long postId) throws NotFoundException {
        ApiMessage<List<Comment>> comments = commentService.findAllPostComments(postId);
        return new ResponseEntity<>(comments, HttpStatus.FOUND);
    }

    @PutMapping("/update-comment/{commentId}")
    public ResponseEntity<ApiMessage<Comment>> editComment(@PathVariable Long commentId, @RequestBody Comment newComment) throws NotNullException {
        ApiMessage<Comment> comment = commentService.updateComment(commentId, newComment);
        return new ResponseEntity<>(comment, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete-comment/{commentId}")
    public ResponseEntity<ApiMessage<String>> deleteComment(@PathVariable Long commentId) {
        ApiMessage<String> response = commentService.deleteCommentById(commentId);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}