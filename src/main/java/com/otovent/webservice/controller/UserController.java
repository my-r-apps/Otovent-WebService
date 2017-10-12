package com.otovent.webservice.controller;

import com.otovent.webservice.entity.User;
import com.otovent.webservice.entity.response.BaseResponse;
import com.otovent.webservice.service.LogUserService;
import com.otovent.webservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
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
}
