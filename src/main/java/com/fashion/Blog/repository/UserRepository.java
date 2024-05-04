package com.fashion.Blog.repository;

import com.fashion.Blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndPassword(String email, String password);
    User findByEmailAndPassword(String email, String password);


}
