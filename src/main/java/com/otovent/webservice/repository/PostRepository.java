package com.otovent.webservice.repository;

import com.otovent.webservice.entity.Posts;
import com.otovent.webservice.entity.User;
import com.otovent.webservice.entity.enums.StatusEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface PostRepository extends JpaRepository<Posts,Long>{
    Page<Posts> findTop10ByUserAndStatusOrderByCreatedDateDesc(User user, StatusEntity status, Pageable pageable);
}
