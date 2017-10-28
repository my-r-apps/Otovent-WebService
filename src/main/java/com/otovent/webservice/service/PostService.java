package com.otovent.webservice.service;

import com.otovent.webservice.entity.Posts;

import java.util.List;

public interface PostService {
    List<Posts> getAllPost();
    void createPost(Posts post);
    void deletePost(Long id);
    void updatePost(Posts post);
}
