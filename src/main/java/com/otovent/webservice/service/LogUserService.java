package com.otovent.webservice.service;

import com.otovent.webservice.entity.User;

public interface LogUserService {
    void insertLogUser(User user, String message);
}
