package com.otovent.webservice.service.impl;

import com.otovent.webservice.entity.User;
import com.otovent.webservice.entity.logs.LogUser;
import com.otovent.webservice.repository.LogRepository;
import com.otovent.webservice.repository.UserRepository;
import com.otovent.webservice.service.LogUserService;
import com.otovent.webservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
