package com.otovent.webservice.controller;

import com.otovent.webservice.entity.Likes;
import com.otovent.webservice.entity.request.LikesRequest;
import com.otovent.webservice.entity.response.BaseResponse;
import com.otovent.webservice.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "like")
public class LikeController {
    @Autowired
    LikeService likeService;

    @GetMapping(value = "/get/all/post" , produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse getAllLikesByPost(@RequestHeader Long id){
        List<Likes> result = likeService.getAllLikesByPost(id);
        return BaseResponse.builder()
                .httpStatus(HttpStatus.OK)
                .result(result)
                .message("Success")
                .build();
    }

    @GetMapping(value = "/get/all/car" , produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse getAllLikesByCars(@RequestHeader Long id){
        List<Likes> result = likeService.getAllLikesByCars(id);
        return BaseResponse.builder()
                .httpStatus(HttpStatus.OK)
                .result(result)
                .message("Success")
                .build();
    }

    @GetMapping(value = "/get/all/comment" , produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse getAllLikesByComments(@RequestHeader Long id){
        List<Likes> result = likeService.getAllLikesByComments(id);
        return BaseResponse.builder()
                .httpStatus(HttpStatus.OK)
                .result(result)
                .message("Success")
                .build();
    }

    @GetMapping(value = "/get" , produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse getOneLike(@RequestHeader Long id){
        List<Likes> result = new ArrayList<>();
        result.add(likeService.getOneLike(id));
        return BaseResponse.builder()
                .httpStatus(HttpStatus.OK)
                .result(result)
                .message("Success")
                .build();
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse doLike(@RequestBody LikesRequest likesRequest){
        List<Boolean> result = new ArrayList<>();
        likeService.doLike(likesRequest);
        result.add(Boolean.TRUE);
        return BaseResponse.builder()
                .httpStatus(HttpStatus.OK)
                .result(result)
                .message("Success")
                .build();
    }

    @PostMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse deleteLike(@RequestBody Long id){
        List<Boolean> result = new ArrayList<>();
        likeService.deleteLike(id);
        result.add(Boolean.TRUE);
        return BaseResponse.builder()
                .httpStatus(HttpStatus.OK)
                .result(result)
                .message("Success")
                .build();
    }
}
