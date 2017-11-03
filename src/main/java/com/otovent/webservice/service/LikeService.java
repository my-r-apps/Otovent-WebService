package com.otovent.webservice.service;

import com.otovent.webservice.entity.Likes;
import com.otovent.webservice.entity.User;
import com.otovent.webservice.entity.request.LikesRequest;

import java.util.List;

public interface LikeService {
    List<Likes> getAllLikesByPost(Long id);
    List<Likes> getAllLikesByCars(Long id);
    List<Likes> getAllLikesByComments(Long id);
    Likes getOneLike(Long id);
    void doLike(LikesRequest likesRequest);
    void deleteLike(Long id);
}
