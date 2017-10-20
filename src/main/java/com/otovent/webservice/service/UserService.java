package com.otovent.webservice.service;

import com.otovent.webservice.entity.User;
import com.otovent.webservice.entity.request.UserRequest;

import java.util.List;

public interface UserService {
    User validateUserById(String username, String password);
    User addOrEditUser(UserRequest userRequest);
    User getDetailOneUser(Long id);
    List<User> getAllUser();
}
