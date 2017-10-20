package com.otovent.webservice.repository;

import com.otovent.webservice.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Posts,Long>{
}
