package com.otovent.webservice.service.impl;

import com.otovent.webservice.entity.Comments;
import com.otovent.webservice.entity.Posts;
import com.otovent.webservice.entity.User;
import com.otovent.webservice.entity.enums.NotificationDependency;
import com.otovent.webservice.entity.enums.StatusEntity;
import com.otovent.webservice.entity.enums.StatusNotification;
import com.otovent.webservice.entity.request.CommentRequest;
import com.otovent.webservice.entity.request.NotificationRequest;
import com.otovent.webservice.repository.CommentRepository;
import com.otovent.webservice.service.CommentService;
import com.otovent.webservice.service.NotificationService;
import com.otovent.webservice.service.PostService;
import com.otovent.webservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserService userService;
    @Autowired
    PostService postService;
    @Autowired
    NotificationService notificationService;

    @Override
    public List<Comments> getAllCommentsByUser(Long idUser) {
        User userTarget = userService.getDetailOneUser(idUser);
        return commentRepository.findAllByUserAndStatusOrderByCreatedDateDesc(userTarget,StatusEntity.ACTIVE);
    }

    @Override
    public List<Comments> getAllCommentsByPost(Long idPost) {
        Posts postTarget = postService.getOnePost(idPost);
        return commentRepository.findAllByPostAndStatusOrderByCreatedDateDesc(postTarget,StatusEntity.ACTIVE);
    }

    @Override
    public Boolean doComment(Long idPost, Long idUser, CommentRequest commentRequest) {
        Date now = new Date();
        User user = userService.getDetailOneUser(idUser);
        Posts postTarget = postService.getOnePost(idPost);
        Comments result = Comments.builder()
                .createdDate(now)
                .commentDetail(commentRequest.getDescription())
                .post(postTarget)
                .status(StatusEntity.ACTIVE)
                .user(user)
                .build();
        commentRepository.save(result);
        NotificationRequest notificationRequest =
                NotificationRequest.builder()
                        .date(new Date())
                        .idCommentLike(commentRepository.findByCreatedDate(now).getId())
                        .idPostEvent(idPost)
                        .notificationDependency(NotificationDependency.COMMENT)
                        .user(user.getId())
                        .statusNotification(StatusNotification.NEW)
                        .build();
        notificationService.addNotification(notificationRequest);
        return Boolean.TRUE;
    }

    @Override
    public Boolean updateComment(Long idPost, Long idUser, CommentRequest commentRequest) {
        User user = userService.getDetailOneUser(idUser);
        Posts postTarget = postService.getOnePost(idPost);
        Comments result = Comments.builder()
                .id(commentRequest.getId())
                .editedDate(new Date())
                .commentDetail(commentRequest.getDescription())
                .post(postTarget)
                .status(StatusEntity.ACTIVE)
                .user(user)
                .build();
        commentRepository.save(result);
        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteComment(Long id) {
        Comments target = commentRepository.findOne(id);
        target.setStatus(StatusEntity.DELETED);
        target.setEditedDate(new Date());
        commentRepository.save(target);
        return Boolean.TRUE;
    }

    @Override
    public Comments getOne(Long idComment) {
        return commentRepository.findOne(idComment);
    }
}
