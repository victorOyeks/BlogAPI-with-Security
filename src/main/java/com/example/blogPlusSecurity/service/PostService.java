package com.example.blogPlusSecurity.service;

import com.example.blogPlusSecurity.DTO.PostDTO;
import com.example.blogPlusSecurity.DTO.PostResponse;

public interface PostService {
//    PostDTO createPost (PostDTO postDTO, Long userId, String token);
    PostDTO createPost (PostDTO postDTO);
    PostResponse getAllPosts(int pageNo, int pagSize, String sortBy, String sortDir);
    PostDTO getPostById(Long id);
    PostDTO updatePost(PostDTO postDTO, Long id);
    void deletePostById(Long id);
}
