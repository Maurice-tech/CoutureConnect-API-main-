package com.fashion.Blog.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorMessageDto {
    private HttpStatus httpStatus;
    private String message;
}
