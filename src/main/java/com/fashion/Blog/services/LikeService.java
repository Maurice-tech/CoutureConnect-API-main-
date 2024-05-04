package com.fashion.Blog.services;

import com.fashion.Blog.exceptions.NotFoundException;
import com.fashion.Blog.exceptions.UnauthorizedException;
import com.fashion.Blog.model.ApiMessage;
import com.fashion.Blog.model.Like;

public interface LikeService {
    ApiMessage<Like> likeAPost(Long postId) throws UnauthorizedException;
    ApiMessage<String> unLikeAPost(Long postId) throws UnauthorizedException, NotFoundException;
    ApiMessage<Like> likeAComment(Long postId) throws UnauthorizedException;

    ApiMessage<String> unLikeAComment(Long commentId) throws UnauthorizedException, NotFoundException;
}
