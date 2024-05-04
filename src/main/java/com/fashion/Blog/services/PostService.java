package com.fashion.Blog.services;

import com.fashion.Blog.dto.PostDto;
import com.fashion.Blog.exceptions.NotFoundException;
import com.fashion.Blog.exceptions.NotNullException;
import com.fashion.Blog.exceptions.UnauthorizedException;
import com.fashion.Blog.model.ApiMessage;
import com.fashion.Blog.model.Post;

import java.util.List;

public interface PostService {
    ApiMessage<Post> createPost(PostDto postDto) throws NotNullException, UnauthorizedException;
    ApiMessage<Post> findPostById(Long postId) throws NotFoundException, UnauthorizedException;
    ApiMessage<List<Post>> findAllPosts() throws NotFoundException, UnauthorizedException;
    ApiMessage<Post> updatePostById(Long postId, PostDto createPostDto) throws NotFoundException, UnauthorizedException, NotNullException;
    void deletePostById(Long postId) throws UnauthorizedException, NotFoundException;

}
