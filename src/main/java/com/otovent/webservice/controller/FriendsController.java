package com.otovent.webservice.controller;

import com.otovent.webservice.entity.Friends;
import com.otovent.webservice.entity.request.FriendRequest;
import com.otovent.webservice.entity.response.BaseResponse;
import com.otovent.webservice.entity.response.PaginationResponse;
import com.otovent.webservice.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "friends")
public class FriendsController {
    @Autowired
    FriendService friendService;

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse getOneFriendship(@RequestHeader Long idFriendship) {
        List<Friends> result = new ArrayList<>();
        Friends friendsShip = friendService.getOne(idFriendship);
        result.add(friendsShip);
        return BaseResponse.builder()
                .result(result)
                .message("Success")
                .httpStatus(HttpStatus.OK)
                .build();
    }
    @GetMapping(value = "/cek/friendship/by/friend", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse cekFriendship(@RequestHeader Long idUser, @RequestHeader Long idFriend) {
        List<Friends> result = new ArrayList<>();
        result.add(friendService.getOneByFriend(idUser,idFriend));
        return BaseResponse.builder()
                .result(result)
                .message("Success")
                .httpStatus(HttpStatus.OK)
                .build();
    }
    @GetMapping(value = "/get/by/request/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public PaginationResponse getAllFriendsByUserRequested(@RequestHeader Long idUser, Pageable pageable){
        Page<Friends> result = friendService.getAllFriendByUser(idUser,pageable);
        return PaginationResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Success")
                .totalPages(result.getTotalPages())
                .result(result)
                .build();
    }
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse addFriend(@RequestBody FriendRequest friendRequest){
        try {
            List<Boolean> result = new ArrayList<>();
            result.add(friendService.addFriend(friendRequest));
            return BaseResponse.builder()
                    .result(result)
                    .message("Success")
                    .httpStatus(HttpStatus.OK)
                    .build();
        }catch(Exception ex){
            return BaseResponse.builder()
                    .result(null)
                    .message(ex.toString())
                    .httpStatus(HttpStatus.OK)
                    .build();
        }
    }
    @GetMapping(value = "/confirm", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse confirmRequest(@RequestHeader Long id){
        List<Boolean> result = new ArrayList<>();
        result.add(friendService.confirmRequest(id));
        return BaseResponse.builder()
                .result(result)
                .message("Success")
                .httpStatus(HttpStatus.OK)
                .build();
    }
    @GetMapping(value = "/reject", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse rejectRequest(@RequestHeader Long id){
        List<Boolean> result = new ArrayList<>();
        result.add(friendService.rejectRequest(id));
        return BaseResponse.builder()
                .result(result)
                .message("Success")
                .httpStatus(HttpStatus.OK)
                .build();
    }
    @GetMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse deleteFried(@RequestHeader Long id){
        List<Boolean> result = new ArrayList<>();
        result.add(friendService.deleteFriend(id));
        return BaseResponse.builder()
                .result(result)
                .message("Success")
                .httpStatus(HttpStatus.OK)
                .build();
    }
}
