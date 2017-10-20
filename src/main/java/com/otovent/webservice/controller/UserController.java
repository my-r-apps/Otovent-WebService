package com.otovent.webservice.controller;

import com.otovent.webservice.entity.User;
import com.otovent.webservice.entity.request.LoginRequest;
import com.otovent.webservice.entity.request.UserRequest;
import com.otovent.webservice.entity.response.BaseResponse;
import com.otovent.webservice.service.LogUserService;
import com.otovent.webservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "users")
public class UserController {
    @Autowired
    UserService  userService;
    @Autowired
    LogUserService logUserService;

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
    @RequestMapping(value="/add",method = RequestMethod.POST, consumes =  MediaType.APPLICATION_JSON_VALUE,
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
}
