package com.fashion.Blog.dto;

import com.fashion.Blog.model.ApiMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FashionDto {
   private String responseCode;
   private String responseMessage;
   private ApiMessage apiMessage;

}
