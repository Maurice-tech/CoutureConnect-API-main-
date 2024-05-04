package com.fashion.Blog.controllers;

import com.fashion.Blog.dto.FashionDto;
import com.fashion.Blog.dto.PostDto;
import com.fashion.Blog.exceptions.NotFoundException;
import com.fashion.Blog.exceptions.NotNullException;
import com.fashion.Blog.exceptions.UnauthorizedException;
import com.fashion.Blog.model.ApiMessage;
import com.fashion.Blog.model.Post;
import com.fashion.Blog.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping ("/api/v1/fashion-blog")
public class PostController {

    private final PostService postService;

    @PostMapping("/blogger/create-post")
    public ResponseEntity<ApiMessage<Post>> createPost(@RequestBody PostDto postDto) throws UnauthorizedException, NotNullException {
        ApiMessage<Post> post =  postService.createPost(postDto);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }
    @GetMapping("/view-post/{postId}")
    public ResponseEntity<ApiMessage<Post>> viewPostById(@PathVariable Long postId) throws NotFoundException, UnauthorizedException {
        ApiMessage<Post> post = postService.findPostById(postId);
        return new ResponseEntity<>(post, HttpStatus.FOUND);
    }

    @GetMapping("/all-posts")
    public ResponseEntity<ApiMessage<List<Post>>> viewAllPosts() throws NotFoundException, UnauthorizedException {
        ApiMessage<List<Post>> allPosts = postService.findAllPosts();
        return new ResponseEntity<>(allPosts, HttpStatus.ACCEPTED);
    }

    @PutMapping("/blogger/update-post/{postId}")
    public ResponseEntity<ApiMessage<Post>> updatePost(@PathVariable Long postId, @RequestBody PostDto PostDto) throws NotFoundException, UnauthorizedException, NotNullException {
        ApiMessage<Post> post = postService.updatePostById(postId, PostDto);
        return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/blogger/delete-post/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId) throws UnauthorizedException, NotFoundException {
        postService.deletePostById(postId);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.ACCEPTED);
    }
}
