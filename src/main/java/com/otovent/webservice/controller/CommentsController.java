package com.otovent.webservice.controller;

import com.otovent.webservice.entity.Comments;
import com.otovent.webservice.entity.request.CommentRequest;
import com.otovent.webservice.entity.response.BaseResponse;
import com.otovent.webservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "comments")
public class CommentsController {
    @Autowired
    CommentService commentService;

    @GetMapping(value = "/get/by/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse getAllCommentByUser(@RequestHeader Long idUser){
        List<Comments> result = commentService.getAllCommentsByUser(idUser);
        return BaseResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Success Get Comment By User")
                .result(result)
                .build();
    }

    @GetMapping(value = "/get/by/post", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse getAllCommentByPost(@RequestHeader Long idPost){
        List<Comments> result = commentService.getAllCommentsByPost(idPost);
        return BaseResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Success Get Comment By Post")
                .result(result)
                .build();
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse doComment(@RequestHeader Long idUser, @RequestHeader Long idPost, @RequestBody CommentRequest commentRequest){
        List<Boolean> result = new ArrayList<>();
        result.add(commentService.doComment(idPost,idUser,commentRequest));
        return BaseResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Success Create Comment")
                .result(result)
                .build();
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse updateComment(@RequestHeader Long idUser, @RequestHeader Long idPost, @RequestBody CommentRequest commentRequest){
        List<Boolean> result = new ArrayList<>();
        result.add(commentService.updateComment(idPost,idUser,commentRequest));
        return BaseResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Success Update")
                .result(result)
                .build();
    }

    @PutMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse deleteComment(@RequestHeader Long id){
        List<Boolean> result = new ArrayList<>();
        result.add(commentService.deleteComment(id));
        return BaseResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Success Delete")
                .result(result)
                .build();
    }
}
