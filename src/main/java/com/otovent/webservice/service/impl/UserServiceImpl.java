package com.otovent.webservice.service.impl;

import com.otovent.webservice.entity.Events;
import com.otovent.webservice.entity.Friends;
import com.otovent.webservice.entity.Posts;
import com.otovent.webservice.entity.User;
import com.otovent.webservice.entity.enums.Role;
import com.otovent.webservice.entity.enums.StatusEntity;
import com.otovent.webservice.entity.request.UserRequest;
import com.otovent.webservice.repository.UserRepository;
import com.otovent.webservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    LogUserService logUserService;
    @Autowired
    PostService postService;
    @Autowired
    EventService eventService;
    @Autowired
    FriendService friendService;

    @Override
    public User validateUserById(String email,String password) {
        User result = userRepository.findOneByEmailAndPasswordAndStatus(email,password,StatusEntity.ACTIVE);
        if(result != null) {
            logUserService.insertLogUser(result,"Success Login");
            return result;
        } else {
            logUserService.insertLogUser(null,"Failed Login");
            return null;
        }
    }

    @Override
    public User addOrEditUser(UserRequest userRequest) {
        User user;
        if(userRequest.getId()==null){
            user = User.builder()
                    .id(userRequest.getId())
                    .email(userRequest.getEmail())
                    .firstName(userRequest.getFirstName())
                    .lastName(userRequest.getLastName())
                    .username(userRequest.getUsername())
                    .password(userRequest.getPassword())
                    .address(userRequest.getAddress())
                    .birthday(userRequest.getBirthday())
                    .linkFacebook(userRequest.getLinkFacebook())
                    .linkInstagram(userRequest.getLinkInstagram())
                    .linkTwitter(userRequest.getLinkTwitter())
                    .phoneNumber(userRequest.getPhoneNumber())
                    .status(StatusEntity.ACTIVE)
                    .role(Role.MEMBER)
                    .build();
        } else {
            user = User.builder()
                    .email(userRequest.getEmail())
                    .firstName(userRequest.getFirstName())
                    .lastName(userRequest.getLastName())
                    .username(userRequest.getUsername())
                    .password(userRequest.getPassword())
                    .address(userRequest.getAddress())
                    .birthday(userRequest.getBirthday())
                    .linkFacebook(userRequest.getLinkFacebook())
                    .linkInstagram(userRequest.getLinkInstagram())
                    .linkTwitter(userRequest.getLinkTwitter())
                    .phoneNumber(userRequest.getPhoneNumber())
                    .role(Role.MEMBER)
                    .status(StatusEntity.ACTIVE)
                    .build();
        }
        userRepository.save(user);
        return user;
    }

    @Override
    public User getDetailOneUser(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public List<User> searchUser(String searchName) {
        return userRepository.
            findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(searchName,searchName);
    }

    @Override
    public Page<? extends Object> getTimeline(Long idUser, Pageable pageable) {
        List<Friends> listOfidFriend = friendService.getAllFriendByUser(idUser,pageable).getContent();
        List<Posts> resultPost = new ArrayList<>();
        List<Events> resultEvent = new ArrayList<>();
        List result = new ArrayList<>();
        for (int i = 0; i < listOfidFriend.size(); i++) {
            Optional.ofNullable(postService.getAllPostByUserAndCreatedDate(listOfidFriend.get(i).getId(),pageable).getContent())
                    .ifPresent(resultPost::addAll);
            Optional.ofNullable(eventService.getAllEventByUserAndCreatedDate(listOfidFriend.get(i).getId(),pageable).getContent())
                    .ifPresent(resultEvent::addAll);
            result.addAll(resultPost);
            result.addAll(resultEvent);
        }
        return new PageImpl<Object>(result);
    }

    @Override
    public Page<? extends Object> getPromotedTimeline(Long idUser, Pageable pageable) {
        List<User> vendorUser = userRepository.findAllByRole(Role.VENDOR);
        List<Posts> resultPost = new ArrayList<>();
        List<Events> resultEvent = new ArrayList<>();
        List result = new ArrayList<>();
        for (User vendor:vendorUser) {
            Optional.ofNullable(postService.getAllPostByUserAndCreatedDate(vendor.getId(),pageable).getContent())
                    .ifPresent(resultPost::addAll);
            Optional.ofNullable(eventService.getAllEventByUserAndCreatedDate(vendor.getId(),pageable).getContent())
                    .ifPresent(resultEvent::addAll);
            result.addAll(resultPost);
            result.addAll(resultEvent);
        }
        return new PageImpl<Object>(result);
    }

    @Override
    public void updatePhotoProfile(Long id, String urlImg){
        User userTarget = userRepository.findOne(id);
        userTarget.setPhotoProfile(urlImg);
        userRepository.save(userTarget);
    }
}
