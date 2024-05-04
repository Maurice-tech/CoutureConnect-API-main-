package com.fashion.Blog.services;

import com.fashion.Blog.dto.FashionDto;
import com.fashion.Blog.dto.UserResponseDto;
import com.fashion.Blog.dto.UserSignupDto;
import com.fashion.Blog.exceptions.AlreadyExistsException;
import com.fashion.Blog.exceptions.NotFoundException;
import com.fashion.Blog.exceptions.NotNullException;
import com.fashion.Blog.model.ApiMessage;

public interface UserService {
    boolean isEmailExist(String email);
    FashionDto signup(UserSignupDto userSignupDto);
    FashionDto login(String email, String password);

}
