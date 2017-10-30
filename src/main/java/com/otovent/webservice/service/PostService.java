package com.otovent.webservice.service;

import com.otovent.webservice.entity.Posts;
import com.otovent.webservice.entity.request.PostRequest;

import java.util.List;

public interface PostService {
    List<Posts> getAllPost();
    Posts getOnePost(Long id);
    Boolean createPost(Long idUser, PostRequest postRequest);
    Boolean deletePost(Long id);
    Boolean updatePost(Long idUser, PostRequest postRequest);
}
