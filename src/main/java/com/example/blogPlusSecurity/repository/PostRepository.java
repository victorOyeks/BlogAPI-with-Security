package com.example.blogPlusSecurity.repository;

import com.example.blogPlusSecurity.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository <Post, Long> {
}
