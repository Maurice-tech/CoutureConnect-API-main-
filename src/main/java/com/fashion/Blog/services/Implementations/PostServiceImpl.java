package com.fashion.Blog.services.Implementations;

import com.fashion.Blog.dto.FashionDto;
import com.fashion.Blog.dto.PostDto;
import com.fashion.Blog.enums.Role;
import com.fashion.Blog.exceptions.NotFoundException;
import com.fashion.Blog.exceptions.NotNullException;
import com.fashion.Blog.exceptions.UnauthorizedException;
import com.fashion.Blog.model.ApiMessage;
import com.fashion.Blog.model.Post;
import com.fashion.Blog.repository.PostRepository;
import com.fashion.Blog.services.PostService;
import com.fashion.Blog.util.LoggedInUser;
import com.fashion.Blog.util.ResponseManager;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final ResponseManager responseManager;
    private final HttpSession httpSession;
    private final LoggedInUser loggedInUser;

    @Override
    public ApiMessage<Post> createPost(PostDto postDto) throws NotNullException, UnauthorizedException {
        if(httpSession.getAttribute("userId") == null)
            throw new UnauthorizedException("Please log in to carry out this operation");
        if(loggedInUser.findLoggedInUser().getRole() != Role.ADMIN)
            throw new UnauthorizedException("You're not authorized to carry out this operation");
        if(postDto.getPostTitle().equals("") || postDto.getPostDescription().equals("") || postDto.getDesignType() == null || postDto.getDesignTypeGender() == null)
            throw new NotNullException("You're missing one of the required inputs");

        Post post = new Post();
        BeanUtils.copyProperties(postDto,post);
        post.setUser(loggedInUser.findLoggedInUser());
        postRepository.save(post);
        return responseManager.success(post);
    }

    @Override
    public ApiMessage<Post> findPostById(Long postId) throws NotFoundException {
        if(postRepository.existsById(postId) == false)
            throw new NotFoundException("No such post");
        Post post = postRepository.findById(postId).get();
        return responseManager.success(post);
    }

    @Override
    public ApiMessage<List<Post>> findAllPosts() throws NotFoundException {
        List<Post> allPosts = postRepository.findAll();
        if(allPosts.size() == 0)
            throw new NotFoundException("No posts yet");
        return responseManager.success(allPosts);
    }

    @Override
    public ApiMessage<Post> updatePostById(Long postId, PostDto postDto) throws NotFoundException, UnauthorizedException {
        if(httpSession.getAttribute("userId") == null)
            throw new UnauthorizedException("Please log in to carry out this operation");
        if(loggedInUser.findLoggedInUser().getRole() != Role.ADMIN)
            throw new UnauthorizedException("You're not authorized to carry out this operation");
        if(postRepository.existsById(postId) == false)
            throw new NotFoundException("No such post");

        Post post = postRepository.findById(postId).get();
        BeanUtils.copyProperties(postDto,post);
        postRepository.save(post);
        return responseManager.success(post);
    }

    @Override
    public void deletePostById(Long postId) throws UnauthorizedException, NotFoundException {
        if(httpSession.getAttribute("userId") == null)
            throw new UnauthorizedException("Please log in to carry out this operation");
        if(loggedInUser.findLoggedInUser().getRole() != Role.ADMIN)
            throw new UnauthorizedException("You're not authorized to carry out this operation");
        if(postRepository.existsById(postId) == false)
            throw new NotFoundException("No such post");
        postRepository.deleteById(postId);
    }
}