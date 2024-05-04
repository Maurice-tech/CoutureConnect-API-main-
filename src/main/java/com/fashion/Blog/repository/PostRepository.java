package com.fashion.Blog.repository;

import com.fashion.Blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
