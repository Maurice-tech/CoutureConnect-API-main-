package com.fashion.Blog.dto;

import com.fashion.Blog.enums.DesignType;
import com.fashion.Blog.enums.DesignTypeGender;
import lombok.Data;

@Data
public class PostDto {
    private String postTitle;
    private String postDescription;
    private DesignType designType;
    private DesignTypeGender designTypeGender;

}
