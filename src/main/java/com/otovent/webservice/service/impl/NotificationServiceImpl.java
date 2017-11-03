package com.otovent.webservice.service.impl;

import com.otovent.webservice.entity.Comments;
import com.otovent.webservice.entity.Likes;
import com.otovent.webservice.entity.Notification;
import com.otovent.webservice.entity.User;
import com.otovent.webservice.entity.enums.NotificationDependency;
import com.otovent.webservice.entity.enums.StatusNotification;
import com.otovent.webservice.entity.request.NotificationRequest;
import com.otovent.webservice.repository.NotificationRepository;
import com.otovent.webservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService{
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    PostService postService;
    @Autowired
    CommentService commentService;
    @Autowired
    LikeService likeService;
    @Autowired
    EventService eventService;
    @Autowired
    UserService userService;

    @Override
    public Page<? extends Object> getAllNotificationByUserAndDate(Long idUser, String dateRequested, Pageable pageable) {
        DateFormat format = new SimpleDateFormat("dd-mm-yyyy");
        Date date = null;
        try {
            date = format.parse(dateRequested);
        } catch (ParseException e) {
            System.out.println(e.toString());
        }
        User userRequested = userService.getDetailOneUser(idUser);
        List<Notification> listOfNotificationUser =  notificationRepository.findAllByUserAndDateOrderByDateDesc
                (userRequested,date,pageable).getContent();
        List result = new ArrayList<>();
        List<Comments> resultComment = new ArrayList<>();
        List<Likes> resultLikes = new ArrayList<>();
        for (Notification notification : listOfNotificationUser) {
            if (notification.getNotificationDependency().equals(NotificationDependency.LIKE))
                Optional.ofNullable(likeService.getOneLike(notification.getIdCommentLike())).ifPresent(resultLikes::add);
            else if (notification.getNotificationDependency().equals(NotificationDependency.COMMENT))
                Optional.ofNullable(commentService.getOne(notification.getIdCommentLike())).ifPresent(resultComment::add);
        }
        result.addAll(resultComment);
        result.addAll(resultLikes);
        return new PageImpl<Object>(result);
    }

    @Override
    public Boolean addNotification(NotificationRequest notificationRequest) {
        User userRequested = userService.getDetailOneUser(notificationRequest.getUser());
        Notification notification = Notification.builder()
                .date(new Date())
                .idCommentLike(notificationRequest.getIdCommentLike())
                .idPostEvent(notificationRequest.getIdPostEvent())
                .statusNotification(StatusNotification.NEW)
                .notificationDependency(notificationRequest.getNotificationDependency())
                .user(userRequested)
                .build();
        notificationRepository.save(notification);
        return Boolean.TRUE;
    }

    @Override
    public Boolean readNotification(Long idNotification) {
        Notification notification = notificationRepository.findOne(idNotification);
        notification.setStatusNotification(StatusNotification.READ);
        notificationRepository.save(notification);
        return Boolean.TRUE;
    }
}
