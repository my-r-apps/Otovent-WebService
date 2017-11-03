package com.otovent.webservice.repository;

import com.otovent.webservice.entity.Comments;
import com.otovent.webservice.entity.Posts;
import com.otovent.webservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comments,Long> {
    List<Comments> findAllByUserOrderByCreatedDateDesc(User user);
    List<Comments> findAllByPostOrderByCreatedDateDesc(Posts post);
    Comments findByCreatedDate(Date createdDate);
}
