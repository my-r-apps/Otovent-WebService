package com.otovent.webservice.service.impl;

import com.otovent.webservice.entity.Posts;
import com.otovent.webservice.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService{
    @Override
    public List<Posts> getAllPost() {
        return null;
    }

    @Override
    public void createPost(Posts post) {

    }

    @Override
    public void deletePost(Long id) {

    }

    @Override
    public void updatePost(Posts post) {

    }
}
