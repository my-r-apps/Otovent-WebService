package com.otovent.webservice.repository;

import com.otovent.webservice.entity.Comments;
import com.otovent.webservice.entity.Posts;
import com.otovent.webservice.entity.User;
import com.otovent.webservice.entity.enums.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comments,Long> {
    List<Comments> findAllByUserAndStatusOrderByCreatedDateDesc(User user, StatusEntity status);
    List<Comments> findAllByPostAndStatusOrderByCreatedDateDesc(Posts post, StatusEntity status);
    Comments findByCreatedDate(Date createdDate);
}
