package com.otovent.webservice.service;

import com.otovent.webservice.entity.Comments;
import com.otovent.webservice.entity.User;
import com.otovent.webservice.entity.request.CommentRequest;

import java.util.List;

public interface CommentService {
    List<Comments> getAllCommentsByUser(Long idUser);
    List<Comments> getAllCommentsByPost(Long idPost);
    Comments getOne(Long idComment);
    Boolean doComment(Long idPost, Long idUser, CommentRequest commentRequest);
    Boolean updateComment(Long idPost, Long idUser, CommentRequest commentRequest);
    Boolean deleteComment(Long id);
}
