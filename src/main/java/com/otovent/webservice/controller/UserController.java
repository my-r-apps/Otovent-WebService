package com.otovent.webservice.controller;

import com.otovent.webservice.entity.Ads;
import com.otovent.webservice.entity.User;
import com.otovent.webservice.entity.request.LoginRequest;
import com.otovent.webservice.entity.request.UserRequest;
import com.otovent.webservice.entity.response.BaseResponse;
import com.otovent.webservice.entity.response.PaginationResponse;
import com.otovent.webservice.service.AdService;
import com.otovent.webservice.service.FriendService;
import com.otovent.webservice.service.LogUserService;
import com.otovent.webservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "users")
public class UserController {
    @Autowired
    ServletContext context;
    @Autowired
    UserService  userService;
    @Autowired
    LogUserService logUserService;
    @Autowired
    AdService adService;

    // TODO Show All Data User
    @RequestMapping(value = "/get/all", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse getAllUsers(){
        List<User> result =  userService.getAllUser();
        if(!result.isEmpty())
            return BaseResponse.builder().httpStatus(HttpStatus.OK).message("Success")
            .result(result).build();
        else
            return BaseResponse.builder().httpStatus(HttpStatus.OK).message("No Users")
                    .result(null)
                    .build();
    }

    // TODO Show One Data User
    @RequestMapping(value = "/get/user", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse getAllUsers(@RequestHeader Long id){
        User user = userService.getDetailOneUser(id);
        List<User> result =  new ArrayList<>();
        result.add(user);
        if(!result.isEmpty())
            return BaseResponse.builder().httpStatus(HttpStatus.OK).message("Success")
                    .result(result).build();
        else
            return BaseResponse.builder().httpStatus(HttpStatus.OK).message("No Users")
                    .result(null)
                    .build();
    }

    // TODO Login User
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse AuthLogin(@RequestBody LoginRequest loginRequest){
        User user = userService.validateUserById(loginRequest.getUsername(),loginRequest.getPassword());
        List<User> result = new ArrayList<>();
        if (user == null){
            return BaseResponse.builder()
                    .httpStatus(HttpStatus.UNAUTHORIZED).message("User Not Exist").result(null).build();
        } else {
            result.add(user);
            return BaseResponse.builder()
                    .httpStatus(HttpStatus.ACCEPTED).message("Success Login").result(result).build();
        }
    }

    // TODO Edit or Add New User
    @RequestMapping(value="/add-edit",method = RequestMethod.POST, consumes =  MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse AddNewUser(@RequestBody UserRequest userRequest){
        List<User> result = new ArrayList<>();
        User user = userService.addOrEditUser(userRequest);
        if(user == null){
            return BaseResponse.builder()
                    .httpStatus(HttpStatus.NOT_ACCEPTABLE).message("User Not Exist").result(null).build();
        } else {
            result.add(user);
            return BaseResponse.builder()
                    .httpStatus(HttpStatus.ACCEPTED).message("Success").result(result).build();
        }
    }

    // TODO to get post and event in timeline
    @GetMapping(value = "/get/timeline", produces = MediaType.APPLICATION_JSON_VALUE)
    public PaginationResponse getUserTimeline(@RequestHeader Long idUser, @RequestHeader String dateRequested, Pageable pageable){
        Page<? extends Object> result = userService.getTimeline(idUser,dateRequested,pageable);
        return PaginationResponse.builder()
                .result(result)
                .totalPages(result.getTotalPages())
                .message("Success")
                .httpStatus(HttpStatus.OK)
                .build();

    }

    // TODO Get List Ads of vendors
    @RequestMapping(value = "/get/promoted", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public PaginationResponse getPromotedTimelineByUserAndDate(@RequestHeader Long id, @RequestHeader String date,
                                                         Pageable pageable){
        Page<? extends Object> result = userService.getPromotedTimeline(id,date,pageable);
        return PaginationResponse.builder()
                .result(result)
                .totalPages(result.getTotalPages())
                .message("Success")
                .httpStatus(HttpStatus.OK)
                .build();
    }

    // TODO Get List Ads of vendors
    @RequestMapping(value = "/search", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse search(@RequestHeader String searchName){
        List<User> result = userService.searchUser(searchName);
        return BaseResponse.builder()
                .result(result)
                .message("Success")
                .httpStatus(HttpStatus.OK)
                .build();
    }
}
