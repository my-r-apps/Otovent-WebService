package com.otovent.webservice.service;

import com.otovent.webservice.entity.Posts;
import com.otovent.webservice.entity.User;
import com.otovent.webservice.entity.request.PostRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface PostService {
    List<Posts> getAllPost();
    Page<Posts> getAllPostByUserAndCreatedDate(Long user, String date, Pageable pageable);
    Posts getOnePost(Long id);
    Boolean createPost(Long idUser, PostRequest postRequest);
    Boolean deletePost(Long id);
    Boolean updatePost(Long idUser, PostRequest postRequest);
    Boolean updateLinkImage(Long id, String urlImage);
}
