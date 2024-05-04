package com.fashion.Blog.dto;

import com.fashion.Blog.enums.Role;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
public class UserSignupDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private String gender;

    @CreationTimestamp
    private Timestamp createdAt;

}
