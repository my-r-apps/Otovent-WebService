package com.otovent.webservice.service;

import com.otovent.webservice.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    Long getIdUserByUsernameAndPassword(String username, String password);
    User validateUserById(String username, String password);
    List<User> getAllUser();
}
