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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.URLDataSource;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
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

    // TODO Controller for Upload User
    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> uploadFile(@RequestParam("uploadfile")MultipartFile uploadFile,
                                        @RequestHeader Long id, @RequestHeader String key) throws IOException {
        if (!uploadFile.getContentType().toLowerCase().contains("jpg")
                && !uploadFile.getContentType().toLowerCase().contains("jpeg")) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
        String keyName = id+key+".jpg";
        try {
            byte[] bytes = uploadFile.getBytes();
            Path path = Paths.get("src/main/resources/scontent/"+keyName);
            Files.write(path,bytes);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // TODO To Get Image From Root Projec / Uploaded
    @RequestMapping(value = "/get/image", method = RequestMethod.GET)
    public BaseResponse getImage(@RequestHeader Long id, @RequestHeader String key) {
        String keyName = id+key+".jpg";
        URLDataSource source = new URLDataSource(this.getClass().getResource("/scontent/"+keyName));
        System.out.println(source.getURL());
        List<String> result = new ArrayList<>();
        result.add(source.getURL().toString());
        return BaseResponse.builder().httpStatus(HttpStatus.OK).message("OK").result(result).build();
    }

}
