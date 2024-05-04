package com.fashion.Blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class ApiMessage<T>{
    private T message;
    private boolean success;
    private T data;


}
