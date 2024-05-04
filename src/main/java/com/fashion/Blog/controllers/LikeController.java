package com.fashion.Blog.controllers;

import com.fashion.Blog.exceptions.NotFoundException;
import com.fashion.Blog.exceptions.UnauthorizedException;
import com.fashion.Blog.model.ApiMessage;
import com.fashion.Blog.model.Like;
import com.fashion.Blog.services.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/fashion-blog")
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/like-post/{postId}")
    public ResponseEntity<ApiMessage<Like>> likePost(@PathVariable Long postId) throws UnauthorizedException {
        ApiMessage<Like> newLike = likeService.likeAPost(postId);
        return new ResponseEntity<>(newLike, HttpStatus.CREATED);
    }

    @PutMapping("/unlike-post/{postId}")
    public ResponseEntity<ApiMessage<String>> unlikePost(@PathVariable Long postId) throws UnauthorizedException, NotFoundException {
        ApiMessage<String> response = likeService.unLikeAPost(postId);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PostMapping("/like-comment/{commentId}")
    public ResponseEntity<ApiMessage<Like>> likeComment(@PathVariable Long commentId) throws UnauthorizedException {
        ApiMessage<Like> newLike = likeService.likeAComment(commentId);
        return new ResponseEntity<>(newLike, HttpStatus.CREATED);
    }

    @PutMapping("/unlike-comment/{commentId}")
    public ResponseEntity<ApiMessage<String>> unlikeComment(@PathVariable Long commentId) throws UnauthorizedException, NotFoundException {
        ApiMessage<String> response = likeService.unLikeAComment(commentId);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
