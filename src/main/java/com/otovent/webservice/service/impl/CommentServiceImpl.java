package com.otovent.webservice.service.impl;

import com.otovent.webservice.entity.Comments;
import com.otovent.webservice.entity.Posts;
import com.otovent.webservice.entity.User;
import com.otovent.webservice.entity.enums.StatusEntity;
import com.otovent.webservice.entity.request.CommentRequest;
import com.otovent.webservice.repository.CommentRepository;
import com.otovent.webservice.service.CommentService;
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

    @Override
    public List<Comments> getAllCommentsByUser(Long idUser) {
        User userTarget = userService.getDetailOneUser(idUser);
        return commentRepository.findAllByUserOrderByCreatedDateDesc(userTarget);
    }

    @Override
    public List<Comments> getAllCommentsByPost(Long idPost) {
        Posts postTarget = postService.getOnePost(idPost);
        return commentRepository.findAllByPostOrderByCreatedDateDesc(postTarget);
    }

    @Override
    public Boolean doComment(Long idPost, Long idUser, CommentRequest commentRequest) {
        User user = userService.getDetailOneUser(idUser);
        Posts postTarget = postService.getOnePost(idPost);
        Comments result = Comments.builder()
                .createdDate(new Date())
                .description(commentRequest.getDescription())
                .post(postTarget)
                .status(StatusEntity.ACTIVE)
                .user(user)
                .build();
        commentRepository.save(result);
        return Boolean.TRUE;
    }

    @Override
    public Boolean updateComment(Long idPost, Long idUser, CommentRequest commentRequest) {
        User user = userService.getDetailOneUser(idUser);
        Posts postTarget = postService.getOnePost(idPost);
        Comments result = Comments.builder()
                .id(commentRequest.getId())
                .editedDate(new Date())
                .description(commentRequest.getDescription())
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
}
