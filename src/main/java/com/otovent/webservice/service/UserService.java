package com.otovent.webservice.service;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    Long getIdUserByUsernameAndPassword(String username, String password);
    Boolean validateUserById(String username,String password);
}
