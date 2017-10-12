package com.otovent.webservice.service.impl;

import com.otovent.webservice.entity.User;
import com.otovent.webservice.entity.logs.LogUser;
import com.otovent.webservice.repository.LogRepository;
import com.otovent.webservice.service.LogUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LogUserServiceImpl implements LogUserService{
    @Autowired
    LogRepository logRepository;

    @Override
    public void insertLogUser(User user, String message) {
        logRepository.save(LogUser.builder()
        .statusLog(message)
        .time(new Date())
        .user(user)
        .build());
    }
}
