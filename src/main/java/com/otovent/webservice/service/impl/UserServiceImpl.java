package com.otovent.webservice.service.impl;

import com.otovent.webservice.entity.User;
import com.otovent.webservice.entity.logs.LogUser;
import com.otovent.webservice.repository.LogRepository;
import com.otovent.webservice.repository.UserRepository;
import com.otovent.webservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Optional;

public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    LogRepository logRepository;

    private void createLogUser(String status,String username){
        Date now = new Date();
        logRepository.save(LogUser.builder()
                .time(now)
                .statusLog(status)
                .username(username)
                .build());
    }

    @Override
    public Long getIdUserByUsernameAndPassword(String username, String password) {
        return userRepository.getDistinctByUsernameAndPassword(username,password);
    }

    @Override
    public Boolean validateUserById(String username,String password) {
        if(Optional.of(userRepository.getOne(getIdUserByUsernameAndPassword(username,password))).isPresent()) {
            createLogUser("Success Login",username);
            return Boolean.TRUE;
        }
        else {
            createLogUser("Failed Login",username);
            return Boolean.FALSE;
        }
    }
}
