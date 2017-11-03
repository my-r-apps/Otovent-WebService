package com.otovent.webservice.service.impl;

import com.otovent.webservice.entity.Likes;
import com.otovent.webservice.entity.User;
import com.otovent.webservice.entity.enums.LikesDependency;
import com.otovent.webservice.entity.enums.NotificationDependency;
import com.otovent.webservice.entity.enums.StatusEntity;
import com.otovent.webservice.entity.enums.StatusNotification;
import com.otovent.webservice.entity.request.LikesRequest;
import com.otovent.webservice.entity.request.NotificationRequest;
import com.otovent.webservice.repository.LikesRepository;
import com.otovent.webservice.service.LikeService;
import com.otovent.webservice.service.NotificationService;
import com.otovent.webservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LikesServiceImpl implements LikeService{
    @Autowired
    LikesRepository likesRepository;
    @Autowired
    UserService userService;
    @Autowired
    NotificationService notificationService;

    @Override
    public List<Likes> getAllLikesByPost(Long id) {
        return likesRepository.findAllByIdTargetAndLikesDependencyAndStatusEntity(id, LikesDependency.POSTS,StatusEntity.ACTIVE);
    }

    @Override
    public List<Likes> getAllLikesByCars(Long id) {
        return likesRepository.findAllByIdTargetAndLikesDependencyAndStatusEntity(id, LikesDependency.CARS,StatusEntity.ACTIVE);
    }

    @Override
    public List<Likes> getAllLikesByComments(Long id) {
        return likesRepository.findAllByIdTargetAndLikesDependencyAndStatusEntity(id, LikesDependency.COMMENTS,StatusEntity.ACTIVE);
    }

    @Override
    public Likes getOneLike(Long id) {
        return likesRepository.findOne(id);
    }

    @Override
    public void doLike(LikesRequest likesRequest) {
        Date now = new Date();
        User user = userService.getDetailOneUser(likesRequest.getUser());
        Likes likes = Likes.builder()
                .idTarget(likesRequest.getIdTarget())
                .likesDependency(likesRequest.getLikesDependency())
                .user(user)
                .createdDate(now)
                .build();
        likesRepository.save(likes);
        NotificationRequest notificationRequest =
                NotificationRequest.builder()
                        .date(new Date())
                        .idCommentLike(likesRepository.findByCreatedDateAndStatusEntity(now,StatusEntity.ACTIVE).getId())
                        .idPostEvent(likesRequest.getIdTarget())
                        .notificationDependency(NotificationDependency.LIKE)
                        .user(user.getId())
                        .statusNotification(StatusNotification.NEW)
                        .build();
        notificationService.addNotification(notificationRequest);
    }

    @Override
    public void deleteLike(Long id) {
        Likes likes = likesRepository.getOne(id);
        likes.setStatusEntity(StatusEntity.DELETED);
        likesRepository.save(likes);
    }
}
