package com.otovent.webservice.service;

import com.otovent.webservice.entity.Comments;
import com.otovent.webservice.entity.User;

import java.util.List;

public interface CommentService {
    List<Comments> getAllCommentsByUser(User user);
    void doComment(Comments comment);
    void deleteComment(Long id);
}
