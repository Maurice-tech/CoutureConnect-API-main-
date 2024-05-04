package com.fashion.Blog.model;

import com.fashion.Blog.enums.Gender;
import com.fashion.Blog.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
@Data

@Entity
@Table(name = "myUser")
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String gender;

    public User(Role role) {
        this.role = role;
    }

    @OneToMany(mappedBy ="user", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    private List<Post> posts;

    @OneToMany(mappedBy ="user", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    private List<Comment> comments;

    @OneToMany(mappedBy ="user", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    private List<Like> likes;


}
