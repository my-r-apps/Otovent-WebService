package com.otovent.webservice.service.impl;

import com.otovent.webservice.entity.User;
import com.otovent.webservice.entity.logs.LogUser;
import com.otovent.webservice.repository.LogUsersRepository;
import com.otovent.webservice.service.LogUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LogUserServiceImpl implements LogUserService{
    @Autowired
    LogUsersRepository logUsersRepository;

    @Override
    public void insertLogUser(User user, String message) {
        logUsersRepository.save(LogUser.builder()
        .statusLog(message)
        .time(new Date())
        .user(user)
        .build());
    }

    @Override
    public List<LogUser> getAllLogUser() {
        return logUsersRepository.findAll();
    }

    @Override
    public List<LogUser> getAllLogUserByDate(String date) {
        Date dateTime = new Date(date);
        return logUsersRepository.findAllByTimeOrderByTimeDesc(dateTime);
    }
}
