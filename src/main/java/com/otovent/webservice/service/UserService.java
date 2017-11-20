package com.otovent.webservice.service;

import com.otovent.webservice.entity.User;
import com.otovent.webservice.entity.request.UserRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface UserService {
    User validateUserById(String email, String password);
    User addOrEditUser(UserRequest userRequest);
    User getDetailOneUser(Long id);
    List<User> getAllUser();
    List<User> searchUser(String searchName);
    Page<? extends Object> getTimeline(Long idUser, Pageable pageable);
    Page<? extends Object> getPromotedTimeline(Long idUser, Pageable pageable);
    void updatePhotoProfile(Long id, String urlImg);
}
