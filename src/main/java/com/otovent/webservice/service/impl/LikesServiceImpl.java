package com.otovent.webservice.service.impl;

import com.otovent.webservice.entity.Likes;
import com.otovent.webservice.entity.User;
import com.otovent.webservice.service.LikeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikesServiceImpl implements LikeService{
    @Override
    public List<Likes> getAllLikesByPost(Long id) {
        return null;
    }

    @Override
    public List<Likes> getAllLikesByCars(Long id) {
        return null;
    }

    @Override
    public List<Likes> getAllLikesByComments(Long id) {
        return null;
    }

    @Override
    public void doLike(Long id, User user) {

    }

    @Override
    public void deleteLike(Long id) {

    }
}
