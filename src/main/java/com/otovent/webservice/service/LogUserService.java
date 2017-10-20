package com.otovent.webservice.service;

import com.otovent.webservice.entity.User;
import com.otovent.webservice.entity.logs.LogUser;

import java.util.List;

public interface LogUserService {
    void insertLogUser(User user, String message);
    List<LogUser> getAllLogUser();
    List<LogUser> getAllLogUserByDate(String date);
}
