package com.fashion.Blog.util;

import com.fashion.Blog.model.ApiMessage;
import org.springframework.stereotype.Service;

@Service
public class ResponseManager<T> {
    public ApiMessage<T> success(T data) {
        return new ApiMessage<T>((T) "Request Successful", true, data);
    }
}
