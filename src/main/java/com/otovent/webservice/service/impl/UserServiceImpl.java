package com.otovent.webservice.service.impl;

import com.otovent.webservice.entity.User;
import com.otovent.webservice.entity.enums.Role;
import com.otovent.webservice.entity.request.UserRequest;
import com.otovent.webservice.repository.UserRepository;
import com.otovent.webservice.service.LogUserService;
import com.otovent.webservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    LogUserService logUserService;

    @Override
    public User validateUserById(String username,String password) {
        User result = userRepository.findOneByUsernameAndPassword(username,password);
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
        if(!userRequest.getId().toString().isEmpty()){
            user = User.builder()
                    .id(userRequest.getId())
                    .email(userRequest.getEmail())
                    .firstName(userRequest.getFirstName())
                    .lastName(userRequest.getLastName())
                    .username(userRequest.getUsername())
                    .password(userRequest.getPassword())
                    .role(Role.MEMBER)
                    .build();
        } else {
            user = User.builder()
                    .email(userRequest.getEmail())
                    .firstName(userRequest.getFirstName())
                    .lastName(userRequest.getLastName())
                    .username(userRequest.getUsername())
                    .password(userRequest.getPassword())
                    .role(Role.MEMBER)
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
    public void updatePhotoProfile(Long id, String urlImg){
        User userTarget = userRepository.findOne(id);
        userTarget.setPhotoProfile(urlImg);
        userRepository.save(userTarget);
    }
}
