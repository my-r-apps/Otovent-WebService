package com.otovent.webservice.service;

import com.otovent.webservice.entity.User;

import java.util.List;

public interface UserService {
    User validateUserById(String username, String password);
    List<User> getAllUser();
}
