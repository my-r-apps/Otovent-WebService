package com.otovent.webservice.controller;

import com.otovent.webservice.entity.Posts;
import com.otovent.webservice.entity.request.PostRequest;
import com.otovent.webservice.entity.response.BaseResponse;
import com.otovent.webservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "posts")
public class PostsController {
    @Autowired
    PostService postService;

    // TODO Get All Posts
    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse getPost(@RequestHeader Long id){
        List<Posts> result = new ArrayList<>();
        result.add(postService.getOnePost(id));
        return BaseResponse.builder().httpStatus(HttpStatus.OK).message("Success").result(result).build();
    }

    // TODO Get All Posts
    @GetMapping(value = "/get/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse getAllPosts(){
        List<Posts> result = postService.getAllPost();
        return BaseResponse.builder().httpStatus(HttpStatus.OK).message("Success").result(result).build();
    }

    // TODO Add Post
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse addPost(@RequestHeader Long id, @RequestBody PostRequest postRequest){
        List<Boolean> result = new ArrayList<>();
        result.add(postService.createPost(id,postRequest));
        return BaseResponse.builder().httpStatus(HttpStatus.OK).message("Success").result(result).build();
    }

    // TODO Edit Post
    @PutMapping(value = "/edit", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse editPost(@RequestHeader Long id, @RequestBody PostRequest postRequest){
        List<Boolean> result = new ArrayList<>();
        result.add(postService.updatePost(id,postRequest));
        return BaseResponse.builder().httpStatus(HttpStatus.OK).message("Success").result(result).build();
    }

    // TODO Delete Post
    @PutMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse deletePost(@RequestHeader Long id){
        List<Boolean> result = new ArrayList<>();
        result.add(postService.deletePost(id));
        return BaseResponse.builder().httpStatus(HttpStatus.OK).message("Success").result(result).build();
    }

}
