package com.otovent.webservice.service.impl;

import com.otovent.webservice.entity.Comments;
import com.otovent.webservice.entity.User;
import com.otovent.webservice.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    @Override
    public List<Comments> getAllCommentsByUser(User user) {
        return null;
    }

    @Override
    public void doComment(Comments comment) {

    }

    @Override
    public void deleteComment(Long id) {

    }
}
