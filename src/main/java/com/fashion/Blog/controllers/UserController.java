package com.fashion.Blog.controllers;

import com.fashion.Blog.dto.FashionDto;
import com.fashion.Blog.dto.UserResponseDto;
import com.fashion.Blog.dto.UserSignupDto;
import com.fashion.Blog.exceptions.AlreadyExistsException;
import com.fashion.Blog.exceptions.NotFoundException;
import com.fashion.Blog.exceptions.NotNullException;
import com.fashion.Blog.model.ApiMessage;
import com.fashion.Blog.model.User;
import com.fashion.Blog.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/fashion-blog")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public FashionDto signup(@RequestBody UserSignupDto userSignupDto){
        return userService.signup(userSignupDto);
    }


    @PostMapping("/login")
    public FashionDto login(@RequestBody User user){
        return userService.login(user.getEmail(), user.getPassword());
    }
}
