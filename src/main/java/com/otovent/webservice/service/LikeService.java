package com.otovent.webservice.service;

import com.otovent.webservice.entity.Likes;
import com.otovent.webservice.entity.User;

import java.util.List;

public interface LikeService {
    List<Likes> getAllLikesByPost(Long id);
    List<Likes> getAllLikesByCars(Long id);
    List<Likes> getAllLikesByComments(Long id);
    void doLike(Long id, User user);
    void deleteLike(Long id);
}
