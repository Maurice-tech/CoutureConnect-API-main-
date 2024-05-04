package com.fashion.Blog.services.Implementations;

import com.fashion.Blog.dto.CommentDto;
import com.fashion.Blog.enums.Role;
import com.fashion.Blog.exceptions.NotFoundException;
import com.fashion.Blog.exceptions.NotNullException;
import com.fashion.Blog.model.ApiMessage;
import com.fashion.Blog.model.Comment;
import com.fashion.Blog.model.Post;
import com.fashion.Blog.model.User;
import com.fashion.Blog.repository.CommentRepository;
import com.fashion.Blog.repository.PostRepository;
import com.fashion.Blog.services.CommentService;
import com.fashion.Blog.util.LoggedInUser;
import com.fashion.Blog.util.ResponseManager;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final HttpSession httpSession;
    private final PostRepository postRepository;
    private final LoggedInUser loggedInUser;
    private final ResponseManager responseManager;



    @Override
    public ApiMessage<Comment> createComment(CommentDto commentDto, Long postId) throws NotNullException, NotFoundException {
        if(commentDto.getComment().equals(""))
            throw new NotNullException("Please type in a comment");

        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDto,comment);
        if(httpSession.getAttribute("userId") == null){
            User user = new User(Role.UNKNOWN_USER);
            comment.setUser(user);
        } else {
            comment.setUser(loggedInUser.findLoggedInUser());
        }

        Post post = postRepository.findById(postId).get();
        if(post == null){
            throw new NotFoundException("This post is not available");
        }
        comment.setPost(post);

        commentRepository.save(comment);
        return responseManager.success(comment);
    }

    @Override
    public ApiMessage<List<Comment>> findAllPostComments(Long postId) throws NotFoundException {
        List<Comment> comments = commentRepository.findAllByPostId(postId);
        if(comments.size() == 0)
            throw new NotFoundException("No comments for this post yet");
        return responseManager.success(comments);
    }

    @Override
    public ApiMessage<Comment> updateComment(Long commentId, Comment newComment) throws NotNullException {
        if(newComment.equals(""))
            throw new NotNullException("Please type in a comment");
        Comment comment = commentRepository.findById(commentId).get();
        comment.setComment(newComment.getComment());
        commentRepository.save(comment);
        return responseManager.success(comment);
    }

    @Override
    public ApiMessage<String> deleteCommentById(Long commentId){
        commentRepository.deleteById(commentId);
        return responseManager.success("Deleted Successfully");
    }
}
