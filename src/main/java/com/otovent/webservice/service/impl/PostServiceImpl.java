package com.otovent.webservice.service.impl;

import com.otovent.webservice.entity.Posts;
import com.otovent.webservice.entity.User;
import com.otovent.webservice.entity.enums.StatusEntity;
import com.otovent.webservice.entity.request.NotificationRequest;
import com.otovent.webservice.entity.request.PostRequest;
import com.otovent.webservice.repository.PostRepository;
import com.otovent.webservice.service.NotificationService;
import com.otovent.webservice.service.PostService;
import com.otovent.webservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService{
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserService userService;

    @Override
    public List<Posts> getAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Page<Posts> getAllPostByUserAndCreatedDate(Long user, Pageable pageable) {
        User userRequested = userService.getDetailOneUser(user);
        return postRepository.findTop10ByUserAndStatusOrderByCreatedDateDesc(userRequested,
                StatusEntity.ACTIVE,pageable);
    }

    @Override
    public Posts getOnePost(Long id) {
        return postRepository.findOne(id);
    }

    @Override
    public Boolean createPost(Long idUser,PostRequest postRequest) {
        Date now = new Date();
        try{
            User userRequest = userService.getDetailOneUser(idUser);
            Posts postResult = Posts.builder()
                    .createdDate(now)
                    .description(postRequest.getDescription())
                    .status(StatusEntity.ACTIVE)
                    .user(userRequest)
                    .build();
            postRepository.save(postResult);
        }catch (Exception ex){
            System.out.println(ex.toString());
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean deletePost(Long id) {
        try{
            Posts postTarget = postRepository.findOne(id);
            postTarget.setEditedDate(new Date());
            postTarget.setStatus(StatusEntity.DELETED);
            postRepository.save(postTarget);
        }catch (Exception ex){
            System.out.println(ex.toString());
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean updatePost(Long id, PostRequest postRequest) {
        try{
            User userRequest = userService.getDetailOneUser(id);
            Posts postResult = Posts.builder()
                    .id(postRequest.getId())
                    .description(postRequest.getDescription())
                    .status(StatusEntity.ACTIVE)
                    .editedDate(new Date())
                    .tipePostEvent("POST")
                    .user(userRequest)
                    .build();
            postRepository.save(postResult);
        }catch (Exception ex){
            System.out.println(ex.toString());
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean updateLinkImage(Long id, String urlImage) {
        Posts post = postRepository.findOne(id);
        post.setImageUrl(urlImage);
        postRepository.save(post);
        return Boolean.TRUE;
    }
}
